package com.edmar.cadastro.application.ports.out;

public interface LoginGateway {


    Boolean buscarUsuario(String usuario, String senha);

}
