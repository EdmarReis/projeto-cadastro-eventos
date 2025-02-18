package com.edmar.cadastro.application.ports.in;

import com.edmar.cadastro.domain.entity.compromisso.EventoCompromisso;

public interface CriarEventoCompromissoUseCase {

    EventoCompromisso executar(EventoCompromisso eventoCompromisso);

}
