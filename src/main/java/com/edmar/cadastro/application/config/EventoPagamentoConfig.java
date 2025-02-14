package com.edmar.cadastro.application.config;

import com.edmar.cadastro.application.ports.in.FinalizaEventoUseCase;
import com.edmar.cadastro.application.ports.out.CriarEventoGateway;
import com.edmar.cadastro.application.ports.out.EnviarEventoGateway;
import com.edmar.cadastro.application.ports.out.FinalizaEventoGateway;
import com.edmar.cadastro.application.usecase.CriarEventoPagamentoUseCase;
import com.edmar.cadastro.application.usecase.EnviaEventoUseCase;
import com.edmar.cadastro.application.usecase.FinalizaEventoUseCaseImpl;
import com.edmar.cadastro.domain.entity.enviar.EventoPagamentoParaEnvio;
import com.edmar.cadastro.infrastructure.gateways.CriarEventoRepositoryGateway;
import com.edmar.cadastro.infrastructure.gateways.EnviarEventoRepositoryGateway;
import com.edmar.cadastro.infrastructure.mapper.EventoItensEntityMapper;
import com.edmar.cadastro.infrastructure.mapper.EventoPagamentoEntityMapper;
import com.edmar.cadastro.infrastructure.persistence.EventoItensRepository;
import com.edmar.cadastro.infrastructure.persistence.EventoRepository;
import com.edmar.cadastro.interfaces.mapper.EventoPagamentoDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Configuration
public class EventoPagamentoConfig {

    @Bean
    CriarEventoPagamentoUseCase createEventoCase(CriarEventoGateway criarEventoGateway) {
        return new CriarEventoPagamentoUseCase(criarEventoGateway);
    }

    @Bean // novo
    EnviaEventoUseCase enviaEventoUseCase(EnviarEventoGateway enviarEventoGateway, RestTemplate restTemplate) {
        return new EnviaEventoUseCase(enviarEventoGateway, restTemplate);
    }

    @Bean // novo
    EnviarEventoGateway enviarEventoGateway(EventoItensRepository eventoItensRepository,
                                            EventoItensEntityMapper eventoItensEntityMapper) {
        return new EnviarEventoRepositoryGateway(eventoItensRepository, eventoItensEntityMapper);
    }

    @Bean //novo
    EventoItensEntityMapper eventoItensEntityMapper(){
        return new EventoItensEntityMapper();
    }

    @Bean
    FinalizaEventoUseCase finalizaEventoUseCase(FinalizaEventoGateway finalizaEventoGateway){
        return new FinalizaEventoUseCaseImpl(finalizaEventoGateway);
    }

    @Bean
    EventoPagamentoParaEnvio eventoPagamentoParaEnvio(){
        return new EventoPagamentoParaEnvio("", "", "", BigDecimal.ZERO,
                "", 0L);
    }

    @Bean
    CriarEventoGateway eventoGateway(EventoRepository eventoRepository,
                                     EventoPagamentoEntityMapper eventoPagamentoEntityMapper,
                                     EventoItensRepository eventoItensRepository) {
        return new CriarEventoRepositoryGateway(eventoRepository, eventoPagamentoEntityMapper,eventoItensRepository);
    }

    @Bean
    EventoPagamentoEntityMapper eventoPagamentoEntityMapper() {
        return new EventoPagamentoEntityMapper();
    }

    @Bean
    EventoPagamentoDTOMapper eventoPagamentoDTOMapper() {
        return new EventoPagamentoDTOMapper();
    }

}
