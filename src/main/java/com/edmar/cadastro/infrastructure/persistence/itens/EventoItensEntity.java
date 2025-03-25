package com.edmar.cadastro.infrastructure.persistence.itens;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

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

    @JoinColumn(name = "id_evento", nullable = false) // Chave estrangeira para EventoEntity
    private Long idEvento;

    private LocalDate dataEvento;
    private String descricao;
    private BigDecimal valor;
    private LocalTime horario;
    private String usuario;
    private Boolean finalizado;
    private String controleEvento;

    public EventoItensEntity(Long idEvento, LocalDate dataEvento, String descricao,
                             BigDecimal valor, String usuario, Boolean finalizado, String controleEvento) {
        this.idEvento = idEvento;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuario;
        this.finalizado = finalizado;
        this.controleEvento = controleEvento;
    }

    public EventoItensEntity(Long idEvento, LocalDate dataEvento, String descricao,
                             LocalTime horario, String usuario, Boolean finalizado, String controleEvento) {
        this.idEvento = idEvento;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.horario = horario;
        this.usuario = usuario;
        this.finalizado = finalizado;
        this.controleEvento = controleEvento;
    }

}
