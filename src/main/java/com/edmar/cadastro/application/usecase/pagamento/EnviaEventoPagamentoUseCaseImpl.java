package com.edmar.cadastro.application.usecase.pagamento;

import com.edmar.cadastro.application.ports.in.RecebePagamentosParaEnvioGateway;
import com.edmar.cadastro.application.ports.out.EnviarEventoGateway;
import com.edmar.cadastro.domain.entity.pagamento.EventoPagamentoParaEnvio;
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
import java.util.*;

@Component
@Slf4j
public class EnviaEventoPagamentoUseCaseImpl implements RecebePagamentosParaEnvioGateway {

    private final EnviarEventoGateway enviarEventoGateway;
    private final RestTemplate restTemplate;

    public EnviaEventoPagamentoUseCaseImpl(@Qualifier("enviarEventoGatewayPagamento") EnviarEventoGateway enviarEventoGateway,
                                             RestTemplate restTemplate) {
        this.enviarEventoGateway = enviarEventoGateway;
        this.restTemplate = restTemplate;
    }

    @Scheduled(fixedRate = 14400000, initialDelay = 300000)
    public void executarAgendamento() {
        log.info("[Cadastro-eventos] Iniciando execução de eventos de pagamentos via @Scheduled");
        processarEventos("Edmar");
    }

    @Override
    public void executarManual(String usuario) {
        log.info("[Cadastro-eventos] Iniciando execução de eventos de pagamentos manual");
        processarEventos(usuario);
    }

    @Override
    public List<Map<String, Object>> executarApp(String usuario) {
        List<Map<String, Object>> allEvents = new ArrayList<>();

        Optional<List<EventoItens>> eventoOptionalEmAtraso = enviarEventoGateway.enviarEventoEmAtraso(usuario);
        Optional<List<EventoItens>> eventoOptional = enviarEventoGateway.enviarEvento(usuario);
        Optional<List<EventoItens>> eventoOptionalDiaMaisUm = enviarEventoGateway.enviarEventoDiaMaisUm(usuario);
        Optional<List<EventoItens>> eventoOptionalDiaMaisDois = enviarEventoGateway.enviarEventoDiaMaisDois(usuario);

        // Adiciona os eventos na ordem correta se estiverem presentes
        eventoOptionalEmAtraso.ifPresent(lista -> lista.forEach(evento -> allEvents.add(eventoParaMap(evento, "Pagamento em atraso"))));
        eventoOptional.ifPresent(lista -> lista.forEach(evento -> allEvents.add(eventoParaMap(evento, "Pagamento para hoje"))));
        eventoOptionalDiaMaisUm.ifPresent(lista -> lista.forEach(evento -> allEvents.add(eventoParaMap(evento, "Pagamento com vencimento para amanhã"))));
        eventoOptionalDiaMaisDois.ifPresent(lista -> lista.forEach(evento -> allEvents.add(eventoParaMap(evento, "Pagamento com vencimento para depois de amanhã"))));

        return allEvents;
    }

    private Map<String, Object> eventoParaMap(EventoItens evento, String acompanhamento) {
        Map<String, Object> map = new HashMap<>();
        map.put("acompanhamento", acompanhamento);
        map.put("descricao", evento.getDescricao());
        map.put("data", evento.getDataEvento());
        map.put("valor", evento.getValor());
        map.put("ocorrencia", evento.getControleEvento());
        map.put("idEvento", evento.getIdOcorrencia());
        return map;
    }

    private void processarEventos(String usuario) {
        Optional<List<EventoItens>> eventoOptionalEmAtraso = enviarEventoGateway.enviarEventoEmAtraso(usuario);
        Optional<List<EventoItens>> eventoOptional = enviarEventoGateway.enviarEvento(usuario);
        Optional<List<EventoItens>> eventoOptionalDiaMaisUm = enviarEventoGateway.enviarEventoDiaMaisUm(usuario);
        Optional<List<EventoItens>> eventoOptionalDiaMaisDois = enviarEventoGateway.enviarEventoDiaMaisDois(usuario);

        String url = "http://localhost:8081/enviar/msg/pagamento";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        eventoOptionalEmAtraso.ifPresent(eventos -> enviarEventos(eventos, "Pagamento em atraso.", url, headers, objectMapper, formatter));
        eventoOptional.ifPresent(eventos -> enviarEventos(eventos, "Pagamento com vencimento para hoje.", url, headers, objectMapper, formatter));
        eventoOptionalDiaMaisUm.ifPresent(eventos -> enviarEventos(eventos, "Pagamento com vencimento para amanhã.", url, headers, objectMapper, formatter));
        eventoOptionalDiaMaisDois.ifPresent(eventos -> enviarEventos(eventos, "Pagamento com vencimento para depois de amanhã.", url, headers, objectMapper, formatter));

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
