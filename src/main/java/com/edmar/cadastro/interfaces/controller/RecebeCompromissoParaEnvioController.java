package com.edmar.cadastro.interfaces.controller;

import com.edmar.cadastro.application.ports.in.RecebeCompromissoParaEnvioGateway;
import com.edmar.cadastro.application.usecase.compromisso.EnviaEventoCompromissoUseCaseImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @PostMapping("/compromissos/app")
    public ResponseEntity<String> recebeParaEnvioApp() {

        // Obtendo os compromissos
        List<Map<String, Object>> compromissos = recebeCompromissoParaEnvioGateway.executarApp();

        // Verificando se a lista está vazia
        /**if (compromissos.isEmpty()) {
            System.out.println("Nenhum compromisso encontrado.");
        } else {
            // Itera sobre cada compromisso e imprime no console
            for (Map<String, Object> compromisso : compromissos) {
                System.out.println("Compromisso recebido:");
                compromisso.forEach((key, value) -> System.out.println(key + ": " + value));
                System.out.println("--------------------------------");
            }
        }**/

        // Convertendo LocalDate e LocalTime para Strings antes de enviar a resposta
        List<Map<String, Object>> compromissosFormatados = compromissos.stream()
                .map(compromisso -> compromisso.entrySet().stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> {
                                    Object value = entry.getValue();
                                    if (value instanceof LocalDate) {
                                        return ((LocalDate) value).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                                    } else if (value instanceof LocalTime) {
                                        return ((LocalTime) value).format(DateTimeFormatter.ofPattern("HH:mm"));
                                    }
                                    return value;
                                }
                        )))
                .collect(Collectors.toList());

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule()); // REGISTRA SUPORTE PARA java.time.*

            String jsonResponse = objectMapper.writeValueAsString(compromissosFormatados);
            return ResponseEntity.ok(jsonResponse);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao gerar JSON: " + e.getMessage());
        }
    }

}
