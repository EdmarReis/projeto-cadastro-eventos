package com.edmar.cadastro.interfaces.controller;

import com.edmar.cadastro.application.usecase.pagamento.CriarEventoPagamentoUseCaseImpl;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;
import com.edmar.cadastro.interfaces.dto.pagamento.CreateEventoPagamentoRequest;
import com.edmar.cadastro.interfaces.dto.pagamento.CreateEventoPagamentoResponse;
import com.edmar.cadastro.interfaces.mapper.EventoPagamentoDTOMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/criarEventoPagamento")
public class CriarEventoPagamentoController {

    private final CriarEventoPagamentoUseCaseImpl criarEventoPagamentoUseCaseImpl;

    private final EventoPagamentoDTOMapper mapper;

    public CriarEventoPagamentoController(CriarEventoPagamentoUseCaseImpl criarEventoPagamentoUseCaseImpl, EventoPagamentoDTOMapper mapper) {
        this.criarEventoPagamentoUseCaseImpl = criarEventoPagamentoUseCaseImpl;
        this.mapper = mapper;
    }


    @PostMapping
    CreateEventoPagamentoResponse create(@Valid @RequestBody CreateEventoPagamentoRequest request) {
        EventoPagamento eventoPagamentoBusinessObj = mapper.toEventoPagamento(request);
        EventoPagamento eventoPagamento = criarEventoPagamentoUseCaseImpl.executar(eventoPagamentoBusinessObj);
        return mapper.toResponse(eventoPagamento);
    }

}
