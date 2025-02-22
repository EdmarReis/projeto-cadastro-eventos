package com.edmar.cadastro.interfaces.controller;

import com.edmar.cadastro.application.ports.in.FinalizaEventoUseCase;
import com.edmar.cadastro.infrastructure.exceptions.EventoNaoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/finalizar")
public class FinalizaEventoController {

    @Autowired
    private FinalizaEventoUseCase finalizaEventoUseCase;

    @PostMapping("/{id}")
    public ResponseEntity<String> finaliza(@PathVariable Long id) throws EventoNaoEncontradoException {
        finalizaEventoUseCase.executar(id);
        return ResponseEntity.ok("Evento com ID " + id + " finalizado com sucesso.");
    }
}
