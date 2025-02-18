package com.edmar.cadastro.interfaces.controller;

import com.edmar.cadastro.application.usecase.compromisso.CriarEventoCompromissoUseCaseImpl;
import com.edmar.cadastro.application.usecase.pagamento.CriarEventoPagamentoUseCaseImpl;
import com.edmar.cadastro.domain.entity.compromisso.EventoCompromisso;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamento;
import com.edmar.cadastro.interfaces.dto.compromisso.CreateEventoCompromissoRequest;
import com.edmar.cadastro.interfaces.dto.compromisso.CreateEventoCompromissoResponse;
import com.edmar.cadastro.interfaces.dto.pagamento.CreateEventoPagamentoRequest;
import com.edmar.cadastro.interfaces.dto.pagamento.CreateEventoPagamentoResponse;
import com.edmar.cadastro.interfaces.mapper.EventoCompromissoDTOMapper;
import com.edmar.cadastro.interfaces.mapper.EventoPagamentoDTOMapper;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/criarEventoCompromisso")
public class CriarEventoCompromissoController {

    private final CriarEventoCompromissoUseCaseImpl criarEventoCompromissoUseCaseImpl;

    private final EventoCompromissoDTOMapper mapper;

    public CriarEventoCompromissoController(CriarEventoCompromissoUseCaseImpl criarEventoCompromissoUseCaseImpl,
                                            EventoCompromissoDTOMapper mapper) {
        this.criarEventoCompromissoUseCaseImpl = criarEventoCompromissoUseCaseImpl;
        this.mapper = mapper;
    }


    @PostMapping
    CreateEventoCompromissoResponse create(@Valid @RequestBody CreateEventoCompromissoRequest request) {
        EventoCompromisso eventoCompromissoBusinessObj = mapper.toEventoPagamento(request);
        EventoCompromisso eventoCompromisso = criarEventoCompromissoUseCaseImpl.executar(eventoCompromissoBusinessObj);
        return mapper.toResponse(eventoCompromisso);
    }

}
