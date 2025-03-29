package com.edmar.cadastro.application.ports.in;


import java.util.List;
import java.util.Map;

public interface RecebePagamentosParaEnvioGateway {

    void executarManual(String usuario);

    List<Map<String, Object>> executarApp(String usuario);
}
