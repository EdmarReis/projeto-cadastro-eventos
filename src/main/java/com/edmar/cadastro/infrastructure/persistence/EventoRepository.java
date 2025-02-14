package com.edmar.cadastro.infrastructure.persistence;

import com.edmar.cadastro.infrastructure.persistence.pagamento.EventoPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<EventoPagamentoEntity, Long> {
}
