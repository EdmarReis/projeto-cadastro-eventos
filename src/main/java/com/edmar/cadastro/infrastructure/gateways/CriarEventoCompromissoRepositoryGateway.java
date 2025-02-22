package com.edmar.cadastro.infrastructure.gateways;

import com.edmar.cadastro.application.ports.out.CriarEventoCompromissoGateway;
import com.edmar.cadastro.domain.entity.compromisso.EventoCompromisso;
import com.edmar.cadastro.infrastructure.mapper.EventoCompromissoEntityMapper;
import com.edmar.cadastro.infrastructure.persistence.compromisso.EventoCompromissoRepository;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensRepository;
import com.edmar.cadastro.infrastructure.persistence.compromisso.EventoCompromissoEntity;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensEntity;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.YearMonth;

@Slf4j
public class CriarEventoCompromissoRepositoryGateway implements CriarEventoCompromissoGateway {

    private final EventoCompromissoRepository eventoCompromissoRepository;
    private final EventoCompromissoEntityMapper eventoCompromissoEntityMapper;
    private final EventoItensRepository eventoItensRepository;

    public CriarEventoCompromissoRepositoryGateway(EventoCompromissoRepository eventoCompromissoRepository,
                                                   EventoCompromissoEntityMapper eventoCompromissoEntityMapper,
                                                   EventoItensRepository eventoItensRepository) {
        this.eventoCompromissoRepository = eventoCompromissoRepository;
        this.eventoCompromissoEntityMapper = eventoCompromissoEntityMapper;
        this.eventoItensRepository = eventoItensRepository;
    }


    @Override
    public EventoCompromisso criarEventoCompromisso(EventoCompromisso eventoCompromisso) {
        log.info("[Cadastro-eventos] Criando novo evento do tipo {} com recorrencia {}.", eventoCompromisso.getTipoEvento(),
                eventoCompromisso.getRecorrencia());
        EventoCompromissoEntity eventoCompromissoEntity = eventoCompromissoEntityMapper.toEntity(eventoCompromisso);
        EventoCompromissoEntity savedObject = eventoCompromissoRepository.save(eventoCompromissoEntity);
        log.info("[Cadastro-eventos] Evento salvo com id {}.", savedObject.getIdEvento());
        switch (eventoCompromisso.getRecorrencia()) {
            case UNICA:
                log.info("[Cadastro-eventos] Criando evento unico atrelado ao id {} na tabela de itens", savedObject.getIdEvento());
                EventoItensEntity eventoCompromissoEntityItens = new EventoItensEntity(savedObject.getIdEvento(),
                        savedObject.getData(), savedObject.getDescricao(), savedObject.getHorario(),
                        savedObject.getUsuario(), false, "Compromisso unico");
                eventoItensRepository.save(eventoCompromissoEntityItens);
                log.info("[Cadastro-eventos] Evento unico salvo na tabela de itens com id {}, atrelado ao evento com id {}.",
                        eventoCompromissoEntityItens.getIdOcorrencia(), eventoCompromissoEntityItens.getIdEvento());
                return eventoCompromissoEntityMapper.toDomain(savedObject);

            case MENSAL:
                log.info("[Cadastro-eventos] Criando eventos mensais atrelados ao id {} na tabela de itens", savedObject.getIdEvento());
                LocalDate dataAtual = savedObject.getData();
                int diaInicial = dataAtual.getDayOfMonth();

                for (int i = 0; i < savedObject.getQuantidadeEventos(); i++) {
                    if (i > 0) {
                        YearMonth mesAno = YearMonth.from(dataAtual).plusMonths(1);
                        int ultimoDia = mesAno.lengthOfMonth();

                        if (diaInicial <= ultimoDia) {
                            dataAtual = LocalDate.of(mesAno.getYear(), mesAno.getMonth(), diaInicial);
                        }
                        else if (diaInicial == 31) {
                            dataAtual = LocalDate.of(mesAno.getYear(), mesAno.getMonth(), (ultimoDia == 30 ? 30 : ultimoDia));
                        }
                        else if (diaInicial == 30) {
                            dataAtual = LocalDate.of(mesAno.getYear(), mesAno.getMonth(), Math.min(30, ultimoDia));
                        }
                    }

                    EventoItensEntity eventoMensal = new EventoItensEntity(
                            savedObject.getIdEvento(),
                            dataAtual,
                            savedObject.getDescricao(),
                            savedObject.getHorario(),
                            savedObject.getUsuario(),
                            false,
                            "Compromisso mensal "+ (i+1) + " de " + savedObject.getQuantidadeEventos()
                    );
                    EventoItensEntity eventoItens = eventoItensRepository.save(eventoMensal);
                    log.info("[Cadastro-eventos] Evento Mensal criado com id {}, agregado ao id {}.",
                            eventoItens.getIdOcorrencia(), eventoItens.getIdEvento());
                }

                return eventoCompromissoEntityMapper.toDomain(savedObject);

            case REPETICAO:
                log.info("[Cadastro-eventos] Criando eventos de compromisso com repetição atrelados ao id {} na tabela de itens", savedObject.getIdEvento());

                LocalDate dataRepeticao = savedObject.getData();
                Long intervaloDias = savedObject.getIntervaloRepeticao();

                if (intervaloDias == null || intervaloDias <= 0) {
                    log.error("[Cadastro-eventos] Erro! Intervalo de repetição inválido.");
                    throw new IllegalArgumentException("O intervalo de repetição deve ser maior que zero.");
                }

                for (int i = 0; i < savedObject.getQuantidadeEventos(); i++) {
                    if (i > 0) {
                        dataRepeticao = dataRepeticao.plusDays(intervaloDias);
                    }

                    EventoItensEntity eventoRepeticao = new EventoItensEntity(
                            savedObject.getIdEvento(),
                            dataRepeticao,
                            savedObject.getDescricao(),
                            savedObject.getHorario(),
                            savedObject.getUsuario(),
                            false,
                            "Compromisso repetição " + (i + 1) + " de " + savedObject.getQuantidadeEventos()
                    );

                    EventoItensEntity eventoItens = eventoItensRepository.save(eventoRepeticao);
                    log.info("[Cadastro-eventos] Evento de repetição criado com id {}, atrelado ao id {}.",
                            eventoItens.getIdOcorrencia(), eventoItens.getIdEvento());
                }

                return eventoCompromissoEntityMapper.toDomain(savedObject);
            default:
                log.error("[Cadastro-eventos] Erro! Tipo de recorrencia inválida.");
                throw new IllegalArgumentException("Tipo de recorrência inválido.");

        }
    }
}
