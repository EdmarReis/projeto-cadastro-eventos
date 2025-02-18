package com.edmar.cadastro.infrastructure.persistence.compromisso;

import com.edmar.cadastro.infrastructure.persistence.compromisso.EventoCompromissoEntity;
import com.edmar.cadastro.infrastructure.persistence.pagamento.EventoPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoCompromissoRepository extends JpaRepository<EventoCompromissoEntity, Long> {
}
