package com.edmar.cadastro.application.usecase.pagamento;

import com.edmar.cadastro.application.ports.out.EnviarEventoGateway;
import com.edmar.cadastro.domain.entity.enviar.EventoPagamentoParaEnvio;
import com.edmar.cadastro.domain.entity.itens.EventoItens;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class EnviaEventoPagamentoUseCaseImpl {

    private final EnviarEventoGateway enviarEventoGateway;
    private final RestTemplate restTemplate;

    public EnviaEventoPagamentoUseCaseImpl(@Qualifier("enviarEventoGatewayPagamento") EnviarEventoGateway enviarEventoGateway,
                                             RestTemplate restTemplate) {
        this.enviarEventoGateway = enviarEventoGateway;
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 300000, initialDelay = 60000)
    public void enviarEvento() throws JsonProcessingException {
        log.info("[Cadastro-eventos] Iniciando o processamento de envio de mensagens de pagamento.");

        Optional<List<EventoItens>> eventoOptionalEmAtraso = enviarEventoGateway.enviarEventoEmAtraso();
        Optional<List<EventoItens>> eventoOptional = enviarEventoGateway.enviarEvento();
        Optional<List<EventoItens>> eventoOptionalDiaMaisUm = enviarEventoGateway.enviarEventoDiaMaisUm();
        Optional<List<EventoItens>> eventoOptionalDiaMaisDois = enviarEventoGateway.enviarEventoDiaMaisDois();

        String url = "http://localhost:8081/enviar/msg/pagamento";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        eventoOptionalEmAtraso.ifPresent(eventos -> enviarEventos(eventos, "Pagamento em atraso.",
                url, headers, objectMapper, formatter));

        eventoOptional.ifPresent(eventos -> enviarEventos(eventos, "Pagamento com vencimento para hoje.",
                url, headers, objectMapper, formatter));

        eventoOptionalDiaMaisUm.ifPresent(eventos -> enviarEventos(eventos, "Pagamento com vencimento para amanhã.",
                url, headers, objectMapper, formatter));

        eventoOptionalDiaMaisDois.ifPresent(eventos -> enviarEventos(eventos, "Pagamento com vencimento para depois de amanhã.",
                url, headers, objectMapper, formatter));

        log.info("[Cadastro-eventos] Processamento de pagamentos finalizado.");
    }

    private void enviarEventos(List<EventoItens> eventos, String mensagem, String url, HttpHeaders headers,
                               ObjectMapper objectMapper, DateTimeFormatter formatter) {
        eventos.forEach(evento -> {
            try {

                EventoPagamentoParaEnvio eventoPagamentoParaEnvio = new EventoPagamentoParaEnvio(
                        mensagem,
                        evento.getDescricao(),
                        evento.getDataEvento().format(formatter),
                        evento.getValor(),
                        evento.getControleEvento(),
                        evento.getIdOcorrencia()
                );

                log.info("[Cadastro-eventos] Enviando evento com ID " + eventoPagamentoParaEnvio.idEvento());

                String json = objectMapper.writeValueAsString(eventoPagamentoParaEnvio);
                HttpEntity<String> entity = new HttpEntity<>(json, headers);
                restTemplate.postForObject(url, entity, String.class);
            } catch (JsonProcessingException e) {
                log.error("[Cadastro-eventos] Erro ao processar JSON para evento com ID " + evento.getIdOcorrencia(), e);
            }
        });
    }
}
