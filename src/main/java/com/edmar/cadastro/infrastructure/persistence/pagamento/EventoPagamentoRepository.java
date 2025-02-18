package com.edmar.cadastro.infrastructure.persistence.pagamento;

import com.edmar.cadastro.infrastructure.persistence.pagamento.EventoPagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoPagamentoRepository extends JpaRepository<EventoPagamentoEntity, Long> {
}
