package com.edmar.cadastro.infrastructure.gateways;

import com.edmar.cadastro.application.ports.out.EnviarEventoGateway;
import com.edmar.cadastro.domain.entity.itens.EventoItens;
import com.edmar.cadastro.infrastructure.mapper.EventoItensPagamentoEntityMapper;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensRepository;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class EnviarEventoPagamentoRepositoryGateway implements EnviarEventoGateway {

    private final EventoItensRepository eventoItensRepository;
    private final EventoItensPagamentoEntityMapper eventoItensPagamentoEntityMapper;

    public EnviarEventoPagamentoRepositoryGateway(EventoItensRepository eventoItensRepository, EventoItensPagamentoEntityMapper eventoItensPagamentoEntityMapper) {
        this.eventoItensRepository = eventoItensRepository;
        this.eventoItensPagamentoEntityMapper = eventoItensPagamentoEntityMapper;
    }

    @Override
    public Optional<List<EventoItens>> enviarEvento(String usuario) {
        LocalDate data = LocalDate.now();

        return eventoItensRepository.findByDataEventoAndFinalizadoFalseAndHorarioIsNullAndUsuarioEquals(data, usuario)
                .map(lista -> lista.stream()
                        .map(eventoItensPagamentoEntityMapper::mapToEventoItens)
                        .toList()
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoDiaMaisUm(String usuario) {
        LocalDate data = LocalDate.now().plusDays(1);

        return eventoItensRepository.findByDataEventoAndFinalizadoFalseAndHorarioIsNullAndUsuarioEquals(data, usuario)
                .map(lista -> lista.stream()
                        .map(eventoItensPagamentoEntityMapper::mapToEventoItens)
                        .toList()
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoDiaMaisDois(String usuario) {
        LocalDate data = LocalDate.now().plusDays(2);

        return eventoItensRepository.findByDataEventoAndFinalizadoFalseAndHorarioIsNullAndUsuarioEquals(data, usuario)
                .map(lista -> lista.stream()
                        .map(eventoItensPagamentoEntityMapper::mapToEventoItens)
                        .toList()
                );
    }

    @Override
    public Optional<List<EventoItens>> enviarEventoEmAtraso(String usuario) {
        LocalDate dataAtual = LocalDate.now();

        return eventoItensRepository.findByDataEventoBeforeAndFinalizadoFalseAndHorarioIsNullAndUsuarioEquals(dataAtual, usuario)
                .map(lista -> lista.stream()
                        .map(eventoItensPagamentoEntityMapper::mapToEventoItens)
                        .sorted(Comparator.comparing(EventoItens::getDataEvento))
                        .toList()
                );
    }
}
