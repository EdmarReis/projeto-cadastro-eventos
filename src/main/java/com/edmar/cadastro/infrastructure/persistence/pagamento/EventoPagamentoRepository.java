package com.edmar.cadastro.infrastructure.persistence.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoPagamentoRepository extends JpaRepository<EventoPagamentoEntity, Long> {
}
