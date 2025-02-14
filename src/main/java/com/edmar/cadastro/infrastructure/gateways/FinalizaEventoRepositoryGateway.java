package com.edmar.cadastro.infrastructure.gateways;

import com.edmar.cadastro.application.ports.out.FinalizaEventoGateway;
import com.edmar.cadastro.infrastructure.exceptions.EventoNaoEncontradoException;
import com.edmar.cadastro.infrastructure.persistence.EventoItensRepository;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FinalizaEventoRepositoryGateway implements FinalizaEventoGateway {

    @Autowired
    private EventoItensRepository eventoItensRepository;


    @Override
    public void finalizarEvento(Long id) throws EventoNaoEncontradoException {
        log.info("[Cadastro-eventos] Tentando finalizar o evento com id {}", id);

        EventoItensEntity evento = eventoItensRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("[Cadastro-eventos] Erro: Evento com id {} não encontrado", id);
                    return new EventoNaoEncontradoException("Evento não encontrado");
                });

        evento.setFinalizado(true);
        eventoItensRepository.save(evento);
        log.info("[Cadastro-eventos] Evento com id {} finalizado com sucesso", id);
    }
}
