package com.edmar.cadastro.application.ports.out;

import com.edmar.cadastro.domain.entity.itens.EventoItens;
import java.util.List;
import java.util.Optional;

public interface EnviarEventoGateway {

    Optional<List<EventoItens>> enviarEvento();

    Optional<List<EventoItens>> enviarEventoDiaMaisUm();

    Optional<List<EventoItens>> enviarEventoDiaMaisDois();

    Optional<List<EventoItens>> enviarEventoEmAtraso();
}
