package com.edmar.cadastro.application.ports.in;

import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;

public interface CriarEventoPagamentoUseCase {

    EventoPagamento executar(EventoPagamento eventoPagamento);

}
