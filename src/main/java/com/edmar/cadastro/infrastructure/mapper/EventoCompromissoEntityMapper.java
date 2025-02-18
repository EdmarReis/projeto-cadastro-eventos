package com.edmar.cadastro.infrastructure.mapper;

import com.edmar.cadastro.domain.entity.compromisso.EventoCompromisso;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;
import com.edmar.cadastro.infrastructure.persistence.compromisso.EventoCompromissoEntity;
import com.edmar.cadastro.infrastructure.persistence.pagamento.EventoPagamentoEntity;

public class EventoCompromissoEntityMapper {

    public EventoCompromissoEntity toEntity(EventoCompromisso eventoCompromisso) {
        return new EventoCompromissoEntity(eventoCompromisso.getDescricao(), eventoCompromisso.getData(),
                eventoCompromisso.getRecorrencia(), eventoCompromisso.getUsuario(), eventoCompromisso.getQuantidadeEventos(),
                eventoCompromisso.getIntervaloRepeticao(), eventoCompromisso.getTipoEvento(),
                eventoCompromisso.getHorario());
    }

    public EventoCompromisso toDomain(EventoCompromissoEntity eventoCompromissoEntity) {
        return new EventoCompromisso(eventoCompromissoEntity.getDescricao(), eventoCompromissoEntity.getData(),
                eventoCompromissoEntity.getRecorrencia(), eventoCompromissoEntity.getId(),
                eventoCompromissoEntity.getUsuario(), eventoCompromissoEntity.getQuantidadeEventos(),
                eventoCompromissoEntity.getIntervaloRepeticao(), eventoCompromissoEntity.getTipoEvento(),
                eventoCompromissoEntity.getHorario());
    }



}
