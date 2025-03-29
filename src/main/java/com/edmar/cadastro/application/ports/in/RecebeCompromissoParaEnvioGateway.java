package com.edmar.cadastro.application.ports.in;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

public interface RecebeCompromissoParaEnvioGateway {

    void executarManual(String usuario);

    List<Map<String, Object>> executarApp(String usuario);
}
