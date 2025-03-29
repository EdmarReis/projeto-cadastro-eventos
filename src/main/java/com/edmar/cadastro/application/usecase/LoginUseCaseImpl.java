package com.edmar.cadastro.application.usecase;

import com.edmar.cadastro.application.ports.in.LoginUseCase;
import com.edmar.cadastro.application.ports.out.LoginGateway;

public class LoginUseCaseImpl implements LoginUseCase {

    public LoginUseCaseImpl(LoginGateway loginGateway) {
        this.loginGateway = loginGateway;
    }

    private LoginGateway loginGateway;
    @Override
    public Boolean buscarUsuario(String usuario, String senha) {
        return loginGateway.buscarUsuario(usuario, senha);
    }
}
