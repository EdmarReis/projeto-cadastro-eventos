package com.edmar.cadastro.application.usecase;

import com.edmar.cadastro.application.ports.in.CriarEventoPagamentoUseCase;
import com.edmar.cadastro.application.ports.out.CriarEventoGateway;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;

public class CriarEventoPagamentoUseCaseImpl implements CriarEventoPagamentoUseCase {

    private CriarEventoGateway criarEventoGateway;

    public CriarEventoPagamentoUseCaseImpl(CriarEventoGateway criarEventoGateway) {
        this.criarEventoGateway = criarEventoGateway;
    }

    @Override
    public EventoPagamento executar(EventoPagamento eventoPagamento) {
        return criarEventoGateway.criarEventoPagamento(eventoPagamento);
    }
}
