package com.edmar.cadastro.application.config;

import com.edmar.cadastro.application.ports.in.FinalizaEventoUseCase;
import com.edmar.cadastro.application.ports.out.CriarEventoCompromissoGateway;
import com.edmar.cadastro.application.ports.out.CriarEventoPagamentoGateway;
import com.edmar.cadastro.application.ports.out.EnviarEventoGateway;
import com.edmar.cadastro.application.ports.out.FinalizaEventoGateway;
import com.edmar.cadastro.application.usecase.compromisso.CriarEventoCompromissoUseCaseImpl;
import com.edmar.cadastro.application.usecase.pagamento.CriarEventoPagamentoUseCaseImpl;
import com.edmar.cadastro.application.usecase.FinalizaEventoUseCaseImpl;
import com.edmar.cadastro.domain.entity.enviar.EventoPagamentoParaEnvio;
import com.edmar.cadastro.infrastructure.gateways.CriarEventoCompromissoRepositoryGateway;
import com.edmar.cadastro.infrastructure.gateways.CriarEventoPagamentoRepositoryGateway;
import com.edmar.cadastro.infrastructure.gateways.EnviarEventoCompromissoRepositoryGateway;
import com.edmar.cadastro.infrastructure.gateways.EnviarEventoPagamentoRepositoryGateway;
import com.edmar.cadastro.infrastructure.mapper.EventoCompromissoEntityMapper;
import com.edmar.cadastro.infrastructure.mapper.EventoItensCompromissoEntityMapper;
import com.edmar.cadastro.infrastructure.mapper.EventoItensPagamentoEntityMapper;
import com.edmar.cadastro.infrastructure.mapper.EventoPagamentoEntityMapper;
import com.edmar.cadastro.infrastructure.persistence.compromisso.EventoCompromissoRepository;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensRepository;
import com.edmar.cadastro.infrastructure.persistence.pagamento.EventoPagamentoRepository;
import com.edmar.cadastro.interfaces.mapper.EventoCompromissoDTOMapper;
import com.edmar.cadastro.interfaces.mapper.EventoPagamentoDTOMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class EventoConfig {

    @Bean
    CriarEventoPagamentoUseCaseImpl createEventoCase(CriarEventoPagamentoGateway criarEventoPagamentoGateway) {
        return new CriarEventoPagamentoUseCaseImpl(criarEventoPagamentoGateway);
    }

    @Bean
    CriarEventoCompromissoUseCaseImpl createEventCompromisso(CriarEventoCompromissoGateway criarEventoCompromissoGateway) {
        return new CriarEventoCompromissoUseCaseImpl(criarEventoCompromissoGateway);
    }


    @Bean // novo
    EnviarEventoGateway enviarEventoGatewayPagamento(EventoItensRepository eventoItensRepository,
                                            EventoItensPagamentoEntityMapper eventoItensPagamentoEntityMapper) {
        return new EnviarEventoPagamentoRepositoryGateway(eventoItensRepository, eventoItensPagamentoEntityMapper);
    }

    @Bean
    EnviarEventoGateway enviarEventoGatewayCompromisso(EventoItensRepository eventoItensRepository,
                                            EventoItensCompromissoEntityMapper eventoItensCompromissoEntityMapper) {
        return new EnviarEventoCompromissoRepositoryGateway(eventoItensRepository, eventoItensCompromissoEntityMapper);
    }

    @Bean //novo
    EventoItensPagamentoEntityMapper eventoItensEntityMapper(){
        return new EventoItensPagamentoEntityMapper();
    }

    @Bean //novo
    EventoItensCompromissoEntityMapper eventoItensCompromissoEntityMapper(){
        return new EventoItensCompromissoEntityMapper();
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
    CriarEventoPagamentoGateway eventoGateway(EventoPagamentoRepository eventoPagamentoRepository,
                                              EventoPagamentoEntityMapper eventoPagamentoEntityMapper,
                                              EventoItensRepository eventoItensRepository) {
        return new CriarEventoPagamentoRepositoryGateway(eventoPagamentoRepository, eventoPagamentoEntityMapper,eventoItensRepository);
    }

    @Bean
    CriarEventoCompromissoGateway eventoCompromissoGateway(EventoCompromissoRepository eventoCompromissoRepository,
                                                           EventoCompromissoEntityMapper eventoCompromissoEntityMapper,
                                                           EventoItensRepository eventoItensRepository) {
        return new CriarEventoCompromissoRepositoryGateway(eventoCompromissoRepository, eventoCompromissoEntityMapper,eventoItensRepository);
    }

    @Bean
    EventoPagamentoEntityMapper eventoPagamentoEntityMapper() {
        return new EventoPagamentoEntityMapper();
    }

    @Bean
    EventoCompromissoEntityMapper eventoCompromissoEntityMapper() {
        return new EventoCompromissoEntityMapper();
    }

    @Bean
    EventoPagamentoDTOMapper eventoPagamentoDTOMapper() {
        return new EventoPagamentoDTOMapper();
    }

    @Bean
    EventoCompromissoDTOMapper eventoCompromissoDTOMapper() {
        return new EventoCompromissoDTOMapper();
    }

    /**@Bean
    public EnviarEventoGateway enviarEventoGateway(EventoItensRepository eventoItensRepository,
                                                   EventoItensCompromissoEntityMapper mapper) {
        return new EnviarEventoCompromissoRepositoryGateway(eventoItensRepository, mapper);
    }**/

}
