package com.edmar.cadastro.infrastructure.gateways;

import com.edmar.cadastro.application.ports.out.CriarEventoGateway;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;
import com.edmar.cadastro.infrastructure.mapper.EventoPagamentoEntityMapper;
import com.edmar.cadastro.infrastructure.persistence.EventoItensRepository;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensEntity;
import com.edmar.cadastro.infrastructure.persistence.pagamento.EventoPagamentoEntity;
import com.edmar.cadastro.infrastructure.persistence.EventoRepository;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.YearMonth;

@Slf4j
public class CriarEventoRepositoryGateway implements CriarEventoGateway {

    private final EventoRepository eventoRepository;
    private final EventoPagamentoEntityMapper eventoPagamentoEntityMapper;
    private final EventoItensRepository eventoItensRepository;

    public CriarEventoRepositoryGateway(EventoRepository eventoRepository, EventoPagamentoEntityMapper eventoPagamentoEntityMapper, EventoItensRepository eventoItensRepository) {
        this.eventoRepository = eventoRepository;
        this.eventoPagamentoEntityMapper = eventoPagamentoEntityMapper;
        this.eventoItensRepository = eventoItensRepository;
    }


    @Override
    public EventoPagamento criarEventoPagamento(EventoPagamento eventoPagamento) {
        log.info("[Cadastro-eventos] Criando novo evento do tipo {} com recorrencia {}.", eventoPagamento.getTipoEvento(),
                eventoPagamento.getRecorrencia());
        EventoPagamentoEntity eventoPagamentoEntity = eventoPagamentoEntityMapper.toEntity(eventoPagamento);
        EventoPagamentoEntity savedObject = eventoRepository.save(eventoPagamentoEntity);
        log.info("[Cadastro-eventos] Evento salvo com id {}.", savedObject.getId());
        switch (eventoPagamento.getRecorrencia()) {
            case UNICA:
                log.info("[Cadastro-eventos] Criando evento unico atrelado ao id {} na tabela de itens", savedObject.getId());
                EventoItensEntity eventoPagamentoEntityItens = new EventoItensEntity(savedObject.getId(),
                        savedObject.getData(), savedObject.getDescricao(), savedObject.getValor(),
                        savedObject.getUsuario(), false, "Evento 1 de 1");
                eventoItensRepository.save(eventoPagamentoEntityItens);
                log.info("[Cadastro-eventos] Evento unico salvo na tabela de itens com id {}, atrelado ao evento com id {}.",
                        eventoPagamentoEntityItens.getIdOcorrencia(), eventoPagamentoEntityItens.getIdAgregacao());
                return eventoPagamentoEntityMapper.toDomain(savedObject);

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
                            savedObject.getValor(),
                            savedObject.getUsuario(),
                            false,
                            "Evento "+ (i+1) + " de " + savedObject.getQuantidadeEventos()
                    );
                    EventoItensEntity eventoItens = eventoItensRepository.save(eventoMensal);
                    log.info("[Cadastro-eventos] Evento Mensal criado com id {}, agregado ao id {}.",
                            eventoItens.getIdOcorrencia(), eventoItens.getIdAgregacao());
                }

                return eventoPagamentoEntityMapper.toDomain(savedObject);

            case REPETICAO:
                System.out.println("Aplicando regras para eventos de recepção...");
                return eventoPagamentoEntityMapper.toDomain(savedObject);
            default:
                log.error("[Cadastro-eventos] Erro! Tipo de recorrencia inválida.");
                throw new IllegalArgumentException("Tipo de recorrência inválido.");

        }
    }
}
