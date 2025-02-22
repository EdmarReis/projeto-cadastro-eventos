package com.edmar.cadastro.interfaces.controller;

import com.edmar.cadastro.application.ports.in.RecebeCompromissoParaEnvioGateway;
import com.edmar.cadastro.application.usecase.compromisso.EnviaEventoCompromissoUseCaseImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/envio")
public class RecebeCompromissoParaEnvioController {

    @Autowired
    private RecebeCompromissoParaEnvioGateway recebeCompromissoParaEnvioGateway;

    @PostMapping("/compromissos")
    public ResponseEntity<String> recebeParaEnvio() {
        recebeCompromissoParaEnvioGateway.executarManual();
        return ResponseEntity.ok("Comando de compromissos executado");
    }

}
