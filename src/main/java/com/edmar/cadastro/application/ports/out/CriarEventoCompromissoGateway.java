package com.edmar.cadastro.application.ports.out;

import com.edmar.cadastro.domain.entity.compromisso.EventoCompromisso;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;

public interface CriarEventoCompromissoGateway {

    EventoCompromisso criarEventoCompromisso(EventoCompromisso eventoCompromisso);

}
