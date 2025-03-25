package com.edmar.cadastro.interfaces.mapper;

import com.edmar.cadastro.domain.entity.compromisso.EventoCompromisso;
import com.edmar.cadastro.interfaces.dto.compromisso.CreateEventoCompromissoRequest;
import com.edmar.cadastro.interfaces.dto.compromisso.CreateEventoCompromissoResponse;

public class EventoCompromissoDTOMapper {

    public CreateEventoCompromissoResponse toResponse(EventoCompromisso eventoCompromisso) {
        return new CreateEventoCompromissoResponse(eventoCompromisso.getDescricao(), eventoCompromisso.getData(),
                eventoCompromisso.getRecorrencia(), eventoCompromisso.getHorario(), eventoCompromisso.getId(),
                eventoCompromisso.getUsuario(), eventoCompromisso.getQuantidadeEventos(),
                eventoCompromisso.getIntervaloRepeticao(), eventoCompromisso.getTipoEvento());
    }

    public EventoCompromisso toEventoPagamento(CreateEventoCompromissoRequest request) {
        return new EventoCompromisso(request.descricao(), request.data(), request.recorrencia(),
                request.id(), request.usuario(), request.intervaloRepeticao(), request.quantidadeEventos(),
                request.tipoEvento(), request.horario());
    }

}
