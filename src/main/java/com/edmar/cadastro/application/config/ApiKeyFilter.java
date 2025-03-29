package com.edmar.cadastro.application.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import java.io.IOException;

@Component
public class ApiKeyFilter extends GenericFilterBean {

    private static final String API_KEY_HEADER = "X-API-KEY";
    private static final String API_KEY_VALUE = "6131fb88-9aa7-433d-95e6-eb4b9a642fa5";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String apiKey = httpRequest.getHeader(API_KEY_HEADER);

        if (apiKey == null || !apiKey.equals(API_KEY_VALUE)) {
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso negado! API Key inválida.");
            return; // Interrompe o processamento da requisição
        }

        chain.doFilter(request, response);
    }
}
