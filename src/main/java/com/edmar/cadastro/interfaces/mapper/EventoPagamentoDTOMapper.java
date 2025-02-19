package com.edmar.cadastro.interfaces.mapper;

import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;
import com.edmar.cadastro.interfaces.dto.pagamento.CreateEventoPagamentoRequest;
import com.edmar.cadastro.interfaces.dto.pagamento.CreateEventoPagamentoResponse;

public class EventoPagamentoDTOMapper {

    public CreateEventoPagamentoResponse toResponse(EventoPagamento eventoPagamento) {
        return new CreateEventoPagamentoResponse(eventoPagamento.getDescricao(), eventoPagamento.getData(),
                eventoPagamento.getRecorrencia(), eventoPagamento.getValor(), eventoPagamento.getId(),
                eventoPagamento.getUsuario(), eventoPagamento.getIntervaloRepeticao(),
                eventoPagamento.getQuantidadeEventos(), eventoPagamento.getTipoEvento());
    }

    public EventoPagamento toEventoPagamento(CreateEventoPagamentoRequest request) {
        return new EventoPagamento(request.descricao(), request.data(), request.recorrencia(),
                request.id(), request.usuario(), request.valor(), request.intervaloRepeticao(),
                request.quantidadeEventos(), request.tipoEvento());
    }

}
