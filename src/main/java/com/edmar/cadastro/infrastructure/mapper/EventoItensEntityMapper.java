package com.edmar.cadastro.infrastructure.mapper;

import com.edmar.cadastro.domain.entity.itens.EventoItens;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensEntity;

public class EventoItensEntityMapper {

    public EventoItens mapToEventoItens(EventoItensEntity entity) {
        return new EventoItens(
                entity.getIdOcorrencia(),
                entity.getIdAgregacao(),
                entity.getDataEvento(),
                entity.getDescricao(),
                entity.getValor(),
                entity.getUsuario(),
                entity.getFinalizado(),
                entity.getControleEvento()
        );
    }

}
