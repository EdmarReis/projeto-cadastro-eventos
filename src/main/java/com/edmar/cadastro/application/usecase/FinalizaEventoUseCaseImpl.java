package com.edmar.cadastro.application.usecase;

import com.edmar.cadastro.application.ports.in.FinalizaEventoUseCase;
import com.edmar.cadastro.application.ports.out.FinalizaEventoGateway;
import com.edmar.cadastro.infrastructure.exceptions.EventoNaoEncontradoException;

public class FinalizaEventoUseCaseImpl implements FinalizaEventoUseCase {

    private final FinalizaEventoGateway finalizaEventoGateway;

    public FinalizaEventoUseCaseImpl(FinalizaEventoGateway finalizaEventoGateway) {
        this.finalizaEventoGateway = finalizaEventoGateway;
    }

    @Override
    public void executar(Long id) throws EventoNaoEncontradoException {
        finalizaEventoGateway.finalizarEvento(id);
    }
}
