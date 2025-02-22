package com.edmar.cadastro.interfaces.dto.pagamento;

import com.edmar.cadastro.domain.entity.Recorrencia;
import com.edmar.cadastro.domain.entity.TipoEvento;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CreateEventoPagamentoResponse(String descricao, LocalDate data,
                                            Recorrencia recorrencia,
                                            BigDecimal valor,
                                            Long id,
                                            String usuario,
                                            Long quantidadeEventos,
                                            Long intervaloRepeticao,
                                            TipoEvento tipoEvento) {
}
