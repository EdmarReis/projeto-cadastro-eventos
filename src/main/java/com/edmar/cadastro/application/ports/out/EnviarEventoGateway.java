package com.edmar.cadastro.application.ports.out;

import com.edmar.cadastro.domain.entity.itens.EventoItens;
import java.util.List;
import java.util.Optional;

public interface EnviarEventoGateway {

    Optional<List<EventoItens>> enviarEvento(String usuario);

    Optional<List<EventoItens>> enviarEventoDiaMaisUm(String usuario);

    Optional<List<EventoItens>> enviarEventoDiaMaisDois(String usuario);

    Optional<List<EventoItens>> enviarEventoEmAtraso(String usuario);
}
