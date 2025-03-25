package com.edmar.cadastro.application.ports.in;


import java.util.List;
import java.util.Map;

public interface RecebePagamentosParaEnvioGateway {

    void executarManual();

    List<Map<String, Object>> executarApp();
}
