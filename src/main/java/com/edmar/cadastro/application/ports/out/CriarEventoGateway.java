package com.edmar.cadastro.application.ports.out;

import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;

public interface CriarEventoGateway {

    EventoPagamento criarEventoPagamento(EventoPagamento eventoPagamento);

}
