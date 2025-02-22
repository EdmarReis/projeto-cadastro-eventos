package com.edmar.cadastro.domain.entity.compromisso;

import com.fasterxml.jackson.annotation.JsonFormat;
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
