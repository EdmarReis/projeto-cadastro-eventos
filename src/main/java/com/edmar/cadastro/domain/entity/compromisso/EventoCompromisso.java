package com.edmar.cadastro.domain.entity.compromisso;

import com.edmar.cadastro.domain.entity.Evento;
import com.edmar.cadastro.domain.entity.Recorrencia;
import com.edmar.cadastro.domain.entity.TipoEvento;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventoCompromisso extends Evento {

    private LocalTime horario;

    public EventoCompromisso(String descricao, LocalDate data, Recorrencia recorrencia, Long id, String usuario,
                             Long quantidadeEventos, Long intervaloRepeticao, TipoEvento tipoEvento,
                             LocalTime horario) {
        super(descricao, data, recorrencia, id, usuario, quantidadeEventos, intervaloRepeticao, tipoEvento);
        this.horario = horario;
    }

    public LocalTime getHorario() {
        return horario;
    }

}
