package com.edmar.cadastro.interfaces.controller;

import com.edmar.cadastro.application.ports.in.RecebePagamentosParaEnvioGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/envio")
public class RecebePagamentosParaEnvioController {

    @Autowired
    private RecebePagamentosParaEnvioGateway recebePagamentosParaEnvioGateway;

    @PostMapping("/pagamentos")
    public ResponseEntity<String> recebeParaEnvio() {
        recebePagamentosParaEnvioGateway.executarManual();
        return ResponseEntity.ok("Comando de Pagamentos executado");
    }

}
