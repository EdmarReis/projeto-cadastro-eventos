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
        log.info("[Cadastro-eventos] Evento salvo com id {}.", savedObject.getId());
        switch (eventoCompromisso.getRecorrencia()) {
            case UNICA:
                log.info("[Cadastro-eventos] Criando evento unico atrelado ao id {} na tabela de itens", savedObject.getId());
                EventoItensEntity eventoCompromissoEntityItens = new EventoItensEntity(savedObject.getId(),
                        savedObject.getData(), savedObject.getDescricao(), savedObject.getHorario(),
                        savedObject.getUsuario(), false, "Evento 1 de 1");
                eventoItensRepository.save(eventoCompromissoEntityItens);
                log.info("[Cadastro-eventos] Evento unico salvo na tabela de itens com id {}, atrelado ao evento com id {}.",
                        eventoCompromissoEntityItens.getIdOcorrencia(), eventoCompromissoEntityItens.getIdAgregacao());
                return eventoCompromissoEntityMapper.toDomain(savedObject);

            case MENSAL:
                log.info("[Cadastro-eventos] Criando eventos mensais atrelados ao id {} na tabela de itens", savedObject.getId());
                LocalDate dataAtual = savedObject.getData(); // Primeira data é a original
                int diaInicial = dataAtual.getDayOfMonth();

                for (int i = 0; i < savedObject.getQuantidadeEventos(); i++) {
                    if (i > 0) { // A partir do segundo mês, calcula a próxima data
                        YearMonth mesAno = YearMonth.from(dataAtual).plusMonths(1);
                        int ultimoDia = mesAno.lengthOfMonth(); // Último dia do mês

                        // Mantém o mesmo dia, se possível
                        if (diaInicial <= ultimoDia) {
                            dataAtual = LocalDate.of(mesAno.getYear(), mesAno.getMonth(), diaInicial);
                        }
                        // Se o dia inicial era 31 e o mês não tem 31 dias, ajusta para 30 ou último dia
                        else if (diaInicial == 31) {
                            dataAtual = LocalDate.of(mesAno.getYear(), mesAno.getMonth(), (ultimoDia == 30 ? 30 : ultimoDia));
                        }
                        // Se o dia inicial era 30, mantém 30 (mesmo que o mês tenha 31 dias)
                        else if (diaInicial == 30) {
                            dataAtual = LocalDate.of(mesAno.getYear(), mesAno.getMonth(), Math.min(30, ultimoDia));
                        }
                    }

                    EventoItensEntity eventoMensal = new EventoItensEntity(
                            savedObject.getId(),
                            dataAtual,
                            savedObject.getDescricao(),
                            savedObject.getHorario(),
                            savedObject.getUsuario(),
                            false,
                            "Evento "+ (i+1) + " de " + savedObject.getQuantidadeEventos()
                    );
                    EventoItensEntity eventoItens = eventoItensRepository.save(eventoMensal);
                    log.info("[Cadastro-eventos] Evento Mensal criado com id {}, agregado ao id {}.",
                            eventoItens.getIdOcorrencia(), eventoItens.getIdAgregacao());
                }

                return eventoCompromissoEntityMapper.toDomain(savedObject);

            case REPETICAO:
                System.out.println("Aplicando regras para eventos de recepção...");
                return eventoCompromissoEntityMapper.toDomain(savedObject);
            default:
                log.error("[Cadastro-eventos] Erro! Tipo de recorrencia inválida.");
                throw new IllegalArgumentException("Tipo de recorrência inválido.");

        }
    }
}
