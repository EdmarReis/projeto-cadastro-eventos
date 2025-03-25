package com.edmar.cadastro.infrastructure.gateways;

import com.edmar.cadastro.application.ports.out.CriarEventoPagamentoGateway;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;
import com.edmar.cadastro.infrastructure.mapper.EventoPagamentoEntityMapper;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensRepository;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensEntity;
import com.edmar.cadastro.infrastructure.persistence.pagamento.EventoPagamentoEntity;
import com.edmar.cadastro.infrastructure.persistence.pagamento.EventoPagamentoRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.YearMonth;

@Slf4j
public class CriarEventoPagamentoRepositoryGateway implements CriarEventoPagamentoGateway {

    private final EventoPagamentoRepository eventoPagamentoRepository;
    private final EventoPagamentoEntityMapper eventoPagamentoEntityMapper;
    private final EventoItensRepository eventoItensRepository;

    public CriarEventoPagamentoRepositoryGateway(EventoPagamentoRepository eventoPagamentoRepository, EventoPagamentoEntityMapper eventoPagamentoEntityMapper, EventoItensRepository eventoItensRepository) {
        this.eventoPagamentoRepository = eventoPagamentoRepository;
        this.eventoPagamentoEntityMapper = eventoPagamentoEntityMapper;
        this.eventoItensRepository = eventoItensRepository;
    }


    @Override
    public EventoPagamento criarEventoPagamento(EventoPagamento eventoPagamento) {
        log.info("[Cadastro-eventos] Criando novo evento do tipo {} com recorrencia {}.", eventoPagamento.getTipoEvento(),
                eventoPagamento.getRecorrencia());
        EventoPagamentoEntity eventoPagamentoEntity = eventoPagamentoEntityMapper.toEntity(eventoPagamento);
        EventoPagamentoEntity savedObject = eventoPagamentoRepository.save(eventoPagamentoEntity);
        log.info("[Cadastro-eventos] Evento salvo com id {}.", savedObject.getIdEvento());
        switch (eventoPagamento.getRecorrencia()) {
            case UNICA:
                log.info("[Cadastro-eventos] Criando evento unico atrelado ao id {} na tabela de itens", savedObject.getIdEvento());
                EventoItensEntity eventoPagamentoEntityItens = new EventoItensEntity(savedObject.getIdEvento(),
                        savedObject.getData(), savedObject.getDescricao(), savedObject.getValor(),
                        savedObject.getUsuario(), false, "Pagamento unico");
                eventoItensRepository.save(eventoPagamentoEntityItens);
                log.info("[Cadastro-eventos] Evento unico salvo na tabela de itens com id {}, atrelado ao evento com id {}.",
                        eventoPagamentoEntityItens.getIdOcorrencia(), eventoPagamentoEntityItens.getIdEvento());
                return eventoPagamentoEntityMapper.toDomain(savedObject);

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
                            savedObject.getValor(),
                            savedObject.getUsuario(),
                            false,
                            "Pagamento mensal "+ (i+1) + " de " + savedObject.getQuantidadeEventos()
                    );
                    EventoItensEntity eventoItens = eventoItensRepository.save(eventoMensal);
                    log.info("[Cadastro-eventos] Evento Mensal criado com id {}, agregado ao id {}.",
                            eventoItens.getIdOcorrencia(), eventoItens.getIdEvento());
                }

                return eventoPagamentoEntityMapper.toDomain(savedObject);

            case REPETICAO:
                log.info("[Cadastro-eventos] Criando eventos de pagamento com recorrencia de repetição atrelados ao id {} na tabela de itens", savedObject.getIdEvento());

                LocalDate dataRepeticao = savedObject.getData(); // Primeira data é a original
                Long intervaloDias = savedObject.getIntervaloRepeticao();

                if (intervaloDias == null || intervaloDias <= 0) {
                    log.error("[Cadastro-eventos] Erro! Intervalo de repetição inválido.");
                    throw new IllegalArgumentException("O intervalo de repetição deve ser maior que zero.");
                }

                for (int i = 0; i < savedObject.getQuantidadeEventos(); i++) {
                    if (i > 0) {
                        dataRepeticao = dataRepeticao.plusDays(intervaloDias); // Adiciona o intervalo de dias
                    }

                    EventoItensEntity eventoRepeticao = new EventoItensEntity(
                            savedObject.getIdEvento(),
                            dataRepeticao,
                            savedObject.getDescricao(),
                            savedObject.getValor(),
                            savedObject.getUsuario(),
                            false,
                            "Pagamento repetição "+ (i+1) + " de " + savedObject.getQuantidadeEventos()
                    );

                    EventoItensEntity eventoItens = eventoItensRepository.save(eventoRepeticao);
                    log.info("[Cadastro-eventos] Evento de repetição criado com id {}, atrelado ao id {}.",
                            eventoItens.getIdOcorrencia(), eventoItens.getIdEvento());
                }

                return eventoPagamentoEntityMapper.toDomain(savedObject);
            default:
                log.error("[Cadastro-eventos] Erro! Tipo de recorrencia inválida.");
                throw new IllegalArgumentException("Tipo de recorrência inválido.");
        }
    }
}
