package com.edmar.cadastro.domain.entity.enviar;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalTime;

public record EventoCompromissoParaEnvio(
        String observacao,
        String descricao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        String data,
        LocalTime horario,
        String controleEvento,
        Long idEvento

) {
}
