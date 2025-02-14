package com.edmar.cadastro.infrastructure.mapper;

import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;
import com.edmar.cadastro.infrastructure.persistence.pagamento.EventoPagamentoEntity;

public class EventoPagamentoEntityMapper {

    public EventoPagamentoEntity toEntity(EventoPagamento eventoPagamento) {
        return new EventoPagamentoEntity(eventoPagamento.getDescricao(), eventoPagamento.getData(),
                eventoPagamento.getRecorrencia(), eventoPagamento.getValor(), eventoPagamento.getUsuario(),
                eventoPagamento.getQuantidadeEventos(), eventoPagamento.getIntervaloRepeticao(),
                eventoPagamento.getTipoEvento());
    }

    public EventoPagamento toDomain(EventoPagamentoEntity eventoPagamentoEntity) {
        return new EventoPagamento(eventoPagamentoEntity.getDescricao(), eventoPagamentoEntity.getData(),
                eventoPagamentoEntity.getRecorrencia(), eventoPagamentoEntity.getId(),
                eventoPagamentoEntity.getUsuario(), eventoPagamentoEntity.getValor(),
                eventoPagamentoEntity.getQuantidadeEventos(), eventoPagamentoEntity.getIntervaloRepeticao(),
                eventoPagamentoEntity.getTipoEvento());
    }



}
