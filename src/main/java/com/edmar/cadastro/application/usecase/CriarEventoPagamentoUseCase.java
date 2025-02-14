package com.edmar.cadastro.application.usecase;

import com.edmar.cadastro.application.ports.out.CriarEventoGateway;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;

public class CriarEventoPagamentoUseCase {

    private CriarEventoGateway criarEventoGateway;

    public CriarEventoPagamentoUseCase(CriarEventoGateway criarEventoGateway) {
        this.criarEventoGateway = criarEventoGateway;
    }

    public EventoPagamento criarEventoPagamento(EventoPagamento eventoPagamento) {
        // criar porta para comunicar com persistencia
        return criarEventoGateway.criarEventoPagamento(eventoPagamento);
    }
}
