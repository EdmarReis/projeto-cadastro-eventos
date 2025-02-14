package com.edmar.cadastro.domain.entity.enviar;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;

public record EventoPagamentoParaEnvio(
        String observacao,
        String descricao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        String data,
        BigDecimal valor,
        String controleEvento,
        Long idEvento

) {
}
