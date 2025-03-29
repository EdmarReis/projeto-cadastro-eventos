package com.edmar.cadastro.application.ports.in;

public interface LoginUseCase {

    Boolean buscarUsuario(String usuario, String senha);

}
