package com.edmar.cadastro.infrastructure.exceptions;

public class EventoNaoEncontradoException extends Exception {

    public EventoNaoEncontradoException(String message) {
        super(message);
    }

    public EventoNaoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }
}
