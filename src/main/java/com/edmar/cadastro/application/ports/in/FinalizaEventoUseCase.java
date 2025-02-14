package com.edmar.cadastro.application.ports.in;

import com.edmar.cadastro.infrastructure.exceptions.EventoNaoEncontradoException;

public interface FinalizaEventoUseCase {

    void executar(Long id) throws EventoNaoEncontradoException;

}
