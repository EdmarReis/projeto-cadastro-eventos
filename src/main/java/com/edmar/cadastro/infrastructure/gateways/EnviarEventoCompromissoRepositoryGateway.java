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
    public Optional<List<EventoItens>> enviarEvento(String usuario) {
        LocalDate data = LocalDate.now();

        return eventoItensRepository.findByDataEventoAndFinalizadoFalseAndValorIsNullAndUsuarioEquals(data, usuario)

                .map(lista -> lista.stream()
                        .map(eventoItensCompromissoEntityMapper::mapToEventoItens) // Converte cada entidade
                        .toList()
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoDiaMaisUm(String usuario) {
        LocalDate data = LocalDate.now().plusDays(1);

        return eventoItensRepository.findByDataEventoAndFinalizadoFalseAndValorIsNullAndUsuarioEquals(data, usuario)
                .map(lista -> lista.stream()
                        .map(eventoItensCompromissoEntityMapper::mapToEventoItens) // Converte cada entidade
                        .toList() // Coleta em uma nova lista
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoDiaMaisDois(String usuario) {
        LocalDate data = LocalDate.now().plusDays(2);

        return eventoItensRepository.findByDataEventoAndFinalizadoFalseAndValorIsNullAndUsuarioEquals(data, usuario)
                .map(lista -> lista.stream()
                        .map(eventoItensCompromissoEntityMapper::mapToEventoItens)
                        .toList()
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoEmAtraso(String usuario) {
        LocalDate dataAtual = LocalDate.now();

        return eventoItensRepository.findByDataEventoBeforeAndFinalizadoFalseAndValorIsNullAndUsuarioEquals(dataAtual, usuario)
                .map(lista -> lista.stream()
                        .map(eventoItensCompromissoEntityMapper::mapToEventoItens)
                        .sorted(Comparator.comparing(EventoItens::getDataEvento))
                        .toList()
                );
    }
}
