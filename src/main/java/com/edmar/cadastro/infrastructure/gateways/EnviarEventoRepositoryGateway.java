package com.edmar.cadastro.infrastructure.gateways;

import com.edmar.cadastro.application.ports.out.EnviarEventoGateway;
import com.edmar.cadastro.domain.entity.itens.EventoItens;
import com.edmar.cadastro.infrastructure.mapper.EventoItensEntityMapper;
import com.edmar.cadastro.infrastructure.persistence.EventoItensRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EnviarEventoRepositoryGateway implements EnviarEventoGateway {

    private final EventoItensRepository eventoItensRepository;
    private final EventoItensEntityMapper eventoItensEntityMapper;

    public EnviarEventoRepositoryGateway(EventoItensRepository eventoItensRepository, EventoItensEntityMapper eventoItensEntityMapper) {
        this.eventoItensRepository = eventoItensRepository;
        this.eventoItensEntityMapper = eventoItensEntityMapper;
    }

    @Override
    public Optional<List<EventoItens>> enviarEvento() {
        LocalDate data = LocalDate.now();

        return eventoItensRepository.findByDataEventoAndFinalizadoFalse(data)
                .map(lista -> lista.stream()
                        .map(eventoItensEntityMapper::mapToEventoItens) // Converte cada entidade
                        .toList() // Coleta em uma nova lista
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoDiaMaisUm() {
        LocalDate data = LocalDate.now().plusDays(1);

        return eventoItensRepository.findByDataEventoAndFinalizadoFalse(data)
                .map(lista -> lista.stream()
                        .map(eventoItensEntityMapper::mapToEventoItens) // Converte cada entidade
                        .toList() // Coleta em uma nova lista
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoDiaMaisDois() {
        LocalDate data = LocalDate.now().plusDays(2);

        return eventoItensRepository.findByDataEventoAndFinalizadoFalse(data)
                .map(lista -> lista.stream()
                        .map(eventoItensEntityMapper::mapToEventoItens) // Converte cada entidade
                        .toList() // Coleta em uma nova lista
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoEmAtraso() {
        LocalDate dataAtual = LocalDate.now();

        return eventoItensRepository.findByDataEventoBeforeAndFinalizadoFalse(dataAtual)
                .map(lista -> lista.stream()
                        .map(eventoItensEntityMapper::mapToEventoItens) // Converte para DTO
                        .sorted(Comparator.comparing(EventoItens::getDataEvento)) // Ordena por data ascendente
                        .toList()
                );
    }
}
