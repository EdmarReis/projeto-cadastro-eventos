package com.edmar.cadastro.infrastructure.persistence.compromisso;

import com.edmar.cadastro.domain.entity.Recorrencia;
import com.edmar.cadastro.domain.entity.TipoEvento;
import com.edmar.cadastro.infrastructure.persistence.EventoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eventos")
public class EventoCompromissoEntity extends EventoEntity {

    @NotNull(message = "O horário não pode ser nulo.")
    private LocalTime horario;

    public EventoCompromissoEntity(String descricao, LocalDate data, Recorrencia recorrencia, String usuario,
                                   Long quantidadeEventos, Long intervaloRepeticao, TipoEvento tipoEvento,
                                   LocalTime horario) {
        super(descricao, data, recorrencia, usuario, quantidadeEventos, intervaloRepeticao, tipoEvento);
        this.horario = horario;
    }
}
