package com.edmar.cadastro.application.ports.out;

import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;

public interface CriarEventoPagamentoGateway {

    EventoPagamento criarEventoPagamento(EventoPagamento eventoPagamento);

}
