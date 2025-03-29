package com.edmar.cadastro.infrastructure.gateways;

import com.edmar.cadastro.application.ports.out.LoginGateway;
import com.edmar.cadastro.infrastructure.persistence.login.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginRepositoryGateway implements LoginGateway {

    public LoginRepositoryGateway(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Boolean buscarUsuario(String usuario, String senha) {
        return loginRepository.existsByUsuarioAndSenha(usuario,senha);
    }
}
