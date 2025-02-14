package com.edmar.cadastro.infrastructure.persistence.itens;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "eventos_itens")
public class EventoItensEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idOcorrencia;
    private Long idAgregacao;
    private LocalDate dataEvento;
    private String descricao;
    private BigDecimal valor;
    private String usuario;
    private Boolean finalizado;
    private String controleEvento;

    public EventoItensEntity(Long idAgregacao, LocalDate dataEvento, String descricao,
                             BigDecimal valor, String usuario, Boolean finalizado, String controleEvento) {
        this.idAgregacao = idAgregacao;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuario;
        this.finalizado = finalizado;
        this.controleEvento = controleEvento;
    }
}
