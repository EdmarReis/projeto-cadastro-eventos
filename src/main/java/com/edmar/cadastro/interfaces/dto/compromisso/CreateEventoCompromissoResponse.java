package com.edmar.cadastro.interfaces.dto.compromisso;

import com.edmar.cadastro.domain.entity.Recorrencia;
import com.edmar.cadastro.domain.entity.TipoEvento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public record CreateEventoCompromissoResponse(String descricao, LocalDate data,
                                              Recorrencia recorrencia,
                                              LocalTime horario,
                                              Long id,
                                              String usuario,
                                              Long quantidadeEventos,
                                              Long intervaloRepeticao,
                                              TipoEvento tipoEvento) {
}
