package com.edmar.cadastro.infrastructure.persistence;

import com.edmar.cadastro.domain.entity.Recorrencia;
import com.edmar.cadastro.domain.entity.TipoEvento;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class EventoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;
    @NotNull(message = "A descricao não pode ser nulo.")
    @NotBlank(message = "A descricao não pode estar vazio ou em branco.")
    private String descricao;
    @FutureOrPresent(message = "A data nao pode ser no passado")
    private LocalDate data;
    @Enumerated(EnumType.STRING)
    private Recorrencia recorrencia;
    @NotNull(message = "O usuário não pode ser nulo.")
    @NotBlank(message = "O usuário não pode estar vazio ou em branco.")
    private String usuario;
    @Min(value = 1, message = "A quantidade de eventos deve ser maior ou igual a 1")
    private Long quantidadeEventos;
    @Min(value = 1, message = "O intervalo de repetição deve ser maior ou igual a 1")
    private Long intervaloRepeticao;
    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    public EventoEntity(String descricao, LocalDate data, Recorrencia recorrencia, String usuario,
                        Long quantidadeEventos, Long intervaloRepeticao, TipoEvento tipoEvento) {
        this.descricao = descricao;
        this.data = data;
        this.recorrencia = recorrencia;
        this.usuario = usuario;
        this.quantidadeEventos = quantidadeEventos;
        this.intervaloRepeticao = intervaloRepeticao;
        this.tipoEvento = tipoEvento;
    }
}
