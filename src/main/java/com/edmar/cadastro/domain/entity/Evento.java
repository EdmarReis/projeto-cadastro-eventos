package com.edmar.cadastro.domain.entity;

import java.time.LocalDate;

public abstract class Evento {

    private Long id;
    private String descricao;
    private LocalDate data;
    private Recorrencia recorrencia;
    private String usuario;
    private Long quantidadeEventos;
    private Long intervaloRepeticao;
    private TipoEvento tipoEvento;

    public Evento(String descricao, LocalDate data, Recorrencia recorrencia, Long id, String usuario,
                  Long intervaloRepeticao, Long quantidadeEventos, TipoEvento tipoEvento) {
        this.descricao = descricao;
        this.data = data;
        this.recorrencia = recorrencia;
        this.id = id;
        this.usuario = usuario;
        this.quantidadeEventos = quantidadeEventos;
        this.intervaloRepeticao = intervaloRepeticao;
        this.tipoEvento = tipoEvento;
    }

    public Evento(String descricao, LocalDate data, Recorrencia recorrencia, String usuario,
                  Long intervaloRepeticao, Long quantidadeEventos, TipoEvento tipoEvento) {
        this.descricao = descricao;
        this.data = data;
        this.recorrencia = recorrencia;
        this.usuario = usuario;
        this.quantidadeEventos = quantidadeEventos;
        this.intervaloRepeticao = intervaloRepeticao;
        this.tipoEvento = tipoEvento;
    }

    public Evento() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Recorrencia getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(Recorrencia recorrencia) {
        this.recorrencia = recorrencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Long getQuantidadeEventos() {
        return quantidadeEventos;
    }

    public void setQuantidadeEventos(Long quantidadeEventos) {
        this.quantidadeEventos = quantidadeEventos;
    }

    public Long getIntervaloRepeticao() {
        return intervaloRepeticao;
    }

    public void setIntervaloRepeticao(Long intervaloRepeticao) {
        this.intervaloRepeticao = intervaloRepeticao;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }
}
