package com.edmar.cadastro.infrastructure.mapper;

import com.edmar.cadastro.domain.entity.compromisso.EventoCompromisso;
import com.edmar.cadastro.infrastructure.persistence.compromisso.EventoCompromissoEntity;

public class EventoCompromissoEntityMapper {

    public EventoCompromissoEntity toEntity(EventoCompromisso eventoCompromisso) {
        return new EventoCompromissoEntity(eventoCompromisso.getDescricao(), eventoCompromisso.getData(),
                eventoCompromisso.getRecorrencia(), eventoCompromisso.getUsuario(), eventoCompromisso.getQuantidadeEventos(),
                eventoCompromisso.getIntervaloRepeticao(), eventoCompromisso.getTipoEvento(),
                eventoCompromisso.getHorario());
    }

    public EventoCompromisso toDomain(EventoCompromissoEntity eventoCompromissoEntity) {
        return new EventoCompromisso(eventoCompromissoEntity.getDescricao(), eventoCompromissoEntity.getData(),
                eventoCompromissoEntity.getRecorrencia(), eventoCompromissoEntity.getIdEvento(),
                eventoCompromissoEntity.getUsuario(), eventoCompromissoEntity.getIntervaloRepeticao(),
                eventoCompromissoEntity.getQuantidadeEventos(), eventoCompromissoEntity.getTipoEvento(),
                eventoCompromissoEntity.getHorario());
    }



}
