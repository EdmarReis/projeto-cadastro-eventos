package com.edmar.cadastro.infrastructure.persistence.pagamento;

import com.edmar.cadastro.domain.entity.Recorrencia;
import com.edmar.cadastro.domain.entity.TipoEvento;
import com.edmar.cadastro.infrastructure.persistence.EventoEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eventos")
public class EventoPagamentoEntity extends EventoEntity {

    @NotNull(message = "O valor não pode ser nulo.")
    @DecimalMin(value = "0.01", inclusive = true, message = "O valor deve ser maior que zero.")
    private BigDecimal valor;

    public EventoPagamentoEntity(String descricao, LocalDate data, Recorrencia recorrencia, BigDecimal valor,
                                 String usuario, Long quantidadeEventos, Long intervaloRepeticao,
                                 TipoEvento tipoEvento) {
        super(descricao, data, recorrencia, usuario, quantidadeEventos, intervaloRepeticao, tipoEvento);
        this.valor = valor;
    }
}
