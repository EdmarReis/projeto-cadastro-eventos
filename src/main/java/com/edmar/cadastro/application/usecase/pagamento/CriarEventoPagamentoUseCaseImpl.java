package com.edmar.cadastro.application.usecase.pagamento;

import com.edmar.cadastro.application.ports.in.CriarEventoPagamentoUseCase;
import com.edmar.cadastro.application.ports.out.CriarEventoPagamentoGateway;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;

public class CriarEventoPagamentoUseCaseImpl implements CriarEventoPagamentoUseCase {

    private CriarEventoPagamentoGateway criarEventoPagamentoGateway;

    public CriarEventoPagamentoUseCaseImpl(CriarEventoPagamentoGateway criarEventoPagamentoGateway) {
        this.criarEventoPagamentoGateway = criarEventoPagamentoGateway;
    }

    @Override
    public EventoPagamento executar(EventoPagamento eventoPagamento) {
        return criarEventoPagamentoGateway.criarEventoPagamento(eventoPagamento);
    }
}
