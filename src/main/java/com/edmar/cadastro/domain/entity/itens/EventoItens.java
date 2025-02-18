package com.edmar.cadastro.domain.entity.itens;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class EventoItens {

    private Long idOcorrencia;
    private Long idAgregacao;
    private LocalDate dataEvento;
    private String descricao;
    private BigDecimal valor;
    private LocalTime horario;
    private String usuario;
    private Boolean finalizado;
    private String controleEvento;

    public EventoItens(Long idOcorrencia, Long idAgregacao, LocalDate dataEvento,
                       String descricao, BigDecimal valor, String usuario, Boolean finalizado,
                       String controleEvento) {
        this.idOcorrencia = idOcorrencia;
        this.idAgregacao = idAgregacao;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuario;
        this.finalizado = finalizado;
        this.controleEvento = controleEvento;
    }

    public EventoItens(Long idOcorrencia, Long idAgregacao, LocalDate dataEvento,
                       String descricao, LocalTime horario, String usuario, Boolean finalizado,
                       String controleEvento) {
        this.idOcorrencia = idOcorrencia;
        this.idAgregacao = idAgregacao;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.horario = horario;
        this.usuario = usuario;
        this.finalizado = finalizado;
        this.controleEvento = controleEvento;
    }

    /**public EventoItens(Long idAgregacao, LocalDate dataEvento, String descricao,
                       BigDecimal valor, String usuario, Boolean finalizado, String controleEvento) {
        this.idAgregacao = idAgregacao;
        this.dataEvento = dataEvento;
        this.descricao = descricao;
        this.valor = valor;
        this.usuario = usuario;
        this.finalizado = finalizado;
        this.controleEvento = controleEvento;
    }**/

    public Long getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Long idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Long getIdAgregacao() {
        return idAgregacao;
    }

    public void setIdAgregacao(Long idAgregacao) {
        this.idAgregacao = idAgregacao;
    }

    public LocalDate getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDate dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public String getControleEvento() {
        return controleEvento;
    }

    public void setControleEvento(String controleEvento) {
        this.controleEvento = controleEvento;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
