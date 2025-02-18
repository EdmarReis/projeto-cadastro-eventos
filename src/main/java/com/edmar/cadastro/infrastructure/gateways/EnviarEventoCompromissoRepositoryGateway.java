package com.edmar.cadastro.infrastructure.gateways;

import com.edmar.cadastro.application.ports.out.EnviarEventoGateway;
import com.edmar.cadastro.domain.entity.itens.EventoItens;
import com.edmar.cadastro.infrastructure.mapper.EventoItensCompromissoEntityMapper;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EnviarEventoCompromissoRepositoryGateway implements EnviarEventoGateway {

    private final EventoItensRepository eventoItensRepository;
    private final EventoItensCompromissoEntityMapper eventoItensCompromissoEntityMapper;

    public EnviarEventoCompromissoRepositoryGateway(EventoItensRepository eventoItensRepository,
                                                    EventoItensCompromissoEntityMapper eventoItensCompromissoEntityMapper) {
        this.eventoItensRepository = eventoItensRepository;
        this.eventoItensCompromissoEntityMapper = eventoItensCompromissoEntityMapper;
    }


    @Override
    public Optional<List<EventoItens>> enviarEvento() {
        LocalDate data = LocalDate.now();

        //return eventoItensRepository.findByDataEventoAndFinalizadoFalse(data)
        return eventoItensRepository.findByDataEventoAndFinalizadoFalseAndValorIsNull(data)

                .map(lista -> lista.stream()
                        .map(eventoItensCompromissoEntityMapper::mapToEventoItens) // Converte cada entidade
                        .toList() // Coleta em uma nova lista
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoDiaMaisUm() {
        LocalDate data = LocalDate.now().plusDays(1);

        //return eventoItensRepository.findByDataEventoAndFinalizadoFalse(data)
        return eventoItensRepository.findByDataEventoAndFinalizadoFalseAndValorIsNull(data)
                .map(lista -> lista.stream()
                        .map(eventoItensCompromissoEntityMapper::mapToEventoItens) // Converte cada entidade
                        .toList() // Coleta em uma nova lista
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoDiaMaisDois() {
        LocalDate data = LocalDate.now().plusDays(2);

        //return eventoItensRepository.findByDataEventoAndFinalizadoFalse(data)
        return eventoItensRepository.findByDataEventoAndFinalizadoFalseAndValorIsNull(data)
                .map(lista -> lista.stream()
                        .map(eventoItensCompromissoEntityMapper::mapToEventoItens) // Converte cada entidade
                        .toList() // Coleta em uma nova lista
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoEmAtraso() {
        LocalDate dataAtual = LocalDate.now();

        //return eventoItensRepository.findByDataEventoBeforeAndFinalizadoFalse(dataAtual)
        return eventoItensRepository.findByDataEventoBeforeAndFinalizadoFalseAndValorIsNull(dataAtual)
                .map(lista -> lista.stream()
                        .map(eventoItensCompromissoEntityMapper::mapToEventoItens) // Converte para DTO
                        .sorted(Comparator.comparing(EventoItens::getDataEvento)) // Ordena por data ascendente
                        .toList()
                );
    }
}
