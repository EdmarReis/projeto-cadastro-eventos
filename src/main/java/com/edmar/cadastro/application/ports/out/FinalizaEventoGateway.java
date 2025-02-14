package com.edmar.cadastro.application.ports.out;

import com.edmar.cadastro.infrastructure.exceptions.EventoNaoEncontradoException;

public interface FinalizaEventoGateway {

    void finalizarEvento(Long id) throws EventoNaoEncontradoException;

}
