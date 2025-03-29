package com.edmar.cadastro.infrastructure.persistence.itens;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EventoItensRepository extends JpaRepository<EventoItensEntity, Long> {

    Optional<List<EventoItensEntity>> findByDataEventoAndFinalizadoFalseAndValorIsNullAndUsuarioEquals(LocalDate dataEvento, String usuario);
    //Optional<List<EventoItensEntity>> findByDataEventoAndFinalizadoFalseAndValorIsNullAndUsuarioEquals(LocalDate dataEvento, String usuario);

    Optional<List<EventoItensEntity>> findByDataEventoBeforeAndFinalizadoFalseAndValorIsNullAndUsuarioEquals(LocalDate dataAtual, String usuario);
    //Optional<List<EventoItensEntity>> findByDataEventoBeforeAndFinalizadoFalseAndValorIsNullAndUsuarioEquals(LocalDate dataAtual, String usuario);


    Optional<List<EventoItensEntity>> findByDataEventoBeforeAndFinalizadoFalseAndHorarioIsNullAndUsuarioEquals(LocalDate dataAtual, String usuario);
    //Optional<List<EventoItensEntity>> findByDataEventoBeforeAndFinalizadoFalseAndHorarioIsNullAndUsuarioEquals(LocalDate dataAtual, String usuario);


    Optional<List<EventoItensEntity>> findByDataEventoAndFinalizadoFalseAndHorarioIsNullAndUsuarioEquals(LocalDate dataAtual, String usuario);
    //Optional<List<EventoItensEntity>> findByDataEventoAndFinalizadoFalseAndHorarioIsNullAndUsuarioEquals(LocalDate dataAtual, String usuario);

}
