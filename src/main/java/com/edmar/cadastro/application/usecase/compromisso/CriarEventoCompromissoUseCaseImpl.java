package com.edmar.cadastro.application.usecase.compromisso;

import com.edmar.cadastro.application.ports.in.CriarEventoCompromissoUseCase;
import com.edmar.cadastro.application.ports.out.CriarEventoCompromissoGateway;
import com.edmar.cadastro.domain.entity.compromisso.EventoCompromisso;

public class CriarEventoCompromissoUseCaseImpl implements CriarEventoCompromissoUseCase {

    private CriarEventoCompromissoGateway criarEventoCompromissoGateway;

    public CriarEventoCompromissoUseCaseImpl(CriarEventoCompromissoGateway criarEventoCompromissoGateway) {
        this.criarEventoCompromissoGateway = criarEventoCompromissoGateway;
    }

    @Override
    public EventoCompromisso executar(EventoCompromisso eventoCompromisso) {
        return criarEventoCompromissoGateway.criarEventoCompromisso(eventoCompromisso);
    }
}
