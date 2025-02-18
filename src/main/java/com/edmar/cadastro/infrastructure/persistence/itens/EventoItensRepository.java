package com.edmar.cadastro.infrastructure.persistence.itens;

import aj.org.objectweb.asm.commons.Remapper;
import com.edmar.cadastro.domain.entity.itens.EventoItens;
import com.edmar.cadastro.infrastructure.persistence.itens.EventoItensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventoItensRepository extends JpaRepository<EventoItensEntity, Long> {

    Optional<List<EventoItensEntity>> findByDataEventoAndFinalizadoFalseAndValorIsNull(LocalDate dataEvento);

    Optional<List<EventoItensEntity>> findByDataEventoBeforeAndFinalizadoFalseAndValorIsNull(LocalDate dataAtual);

    Optional<List<EventoItensEntity>> findByDataEventoBeforeAndFinalizadoFalseAndHorarioIsNull(LocalDate dataAtual);

    Optional<List<EventoItensEntity>> findByDataEventoAndFinalizadoFalseAndHorarioIsNull(LocalDate dataAtual);
}
