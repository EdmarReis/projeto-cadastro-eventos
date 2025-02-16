package com.edmar.cadastro.application.config;

import com.edmar.cadastro.application.ports.in.FinalizaEventoUseCase;
import com.edmar.cadastro.application.ports.out.CriarEventoGateway;
import com.edmar.cadastro.application.ports.out.EnviarEventoGateway;
import com.edmar.cadastro.application.ports.out.FinalizaEventoGateway;
import com.edmar.cadastro.application.usecase.CriarEventoPagamentoUseCaseImpl;
import com.edmar.cadastro.application.usecase.EnviaEventoUseCaseImpl;
import com.edmar.cadastro.application.usecase.FinalizaEventoUseCaseImpl;
import com.edmar.cadastro.domain.entity.enviar.EventoPagamentoParaEnvio;
import com.edmar.cadastro.infrastructure.gateways.CriarEventoPagamentoRepositoryGateway;
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
    CriarEventoPagamentoUseCaseImpl createEventoCase(CriarEventoGateway criarEventoGateway) {
        return new CriarEventoPagamentoUseCaseImpl(criarEventoGateway);
    }

    //@Bean // novo
    //EnviaEventoUseCaseImpl enviaEventoUseCase(EnviarEventoGateway enviarEventoGateway, RestTemplate restTemplate) {
      //  return new EnviaEventoUseCaseImpl(enviarEventoGateway, restTemplate);
    //}

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
        return new CriarEventoPagamentoRepositoryGateway(eventoRepository, eventoPagamentoEntityMapper,eventoItensRepository);
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
