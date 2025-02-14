package com.edmar.cadastro.infrastructure.persistence;

import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventoItensRepository extends JpaRepository<EventoItensEntity, Long> {

    Optional<List<EventoItensEntity>> findByDataEventoAndFinalizadoFalse(LocalDate dataEvento);
    Optional<List<EventoItensEntity>> findByDataEventoBeforeAndFinalizadoFalse(LocalDate dataEvento);

}
