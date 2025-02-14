package com.edmar.cadastro.interfaces.controller;

import com.edmar.cadastro.application.usecase.CriarEventoPagamentoUseCase;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;
import com.edmar.cadastro.interfaces.dto.CreateEventoPagamentoRequest;
import com.edmar.cadastro.interfaces.dto.CreateEventoPagamentoResponse;
import com.edmar.cadastro.interfaces.mapper.EventoPagamentoDTOMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/criarEventoPagamento")
public class EventoPagamentoController {

    private final CriarEventoPagamentoUseCase criarEventoPagamentoUseCase;

    private final EventoPagamentoDTOMapper mapper;

    public EventoPagamentoController(CriarEventoPagamentoUseCase criarEventoPagamentoUseCase, EventoPagamentoDTOMapper mapper) {
        this.criarEventoPagamentoUseCase = criarEventoPagamentoUseCase;
        this.mapper = mapper;
    }


    @PostMapping
    CreateEventoPagamentoResponse create(@Valid @RequestBody CreateEventoPagamentoRequest request) {
        EventoPagamento eventoPagamentoBusinessObj = mapper.toEventoPagamento(request);
        EventoPagamento eventoPagamento = criarEventoPagamentoUseCase.criarEventoPagamento(eventoPagamentoBusinessObj);
        return mapper.toResponse(eventoPagamento);
    }

}
