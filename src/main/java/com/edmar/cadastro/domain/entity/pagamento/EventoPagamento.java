package com.edmar.cadastro.domain.entity.pagamento;

import com.edmar.cadastro.domain.entity.Evento;
import com.edmar.cadastro.domain.entity.Recorrencia;
import com.edmar.cadastro.domain.entity.TipoEvento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EventoPagamento extends Evento {

    private BigDecimal valor;

    public EventoPagamento(String descricao, LocalDate data, Recorrencia recorrencia, Long id,
                           String usuario, BigDecimal valor, Long quantidadeEvento, Long intervaloRepeticao,
                           TipoEvento tipoEvento) {
        super(descricao, data, recorrencia, id, usuario, quantidadeEvento, intervaloRepeticao, tipoEvento);
        this.valor = valor;
    }

    public EventoPagamento(String descricao, LocalDate data, Recorrencia recorrencia, BigDecimal valor,
                           String usuario, Long quantidadeEvento, Long intervaloRepeticao,
                           TipoEvento tipoEvento) {
        super(descricao, data, recorrencia, usuario, quantidadeEvento, intervaloRepeticao, tipoEvento);
        this.valor = valor;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

}
