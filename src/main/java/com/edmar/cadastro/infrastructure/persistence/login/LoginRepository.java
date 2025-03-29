package com.edmar.cadastro.infrastructure.persistence.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {

    Boolean existsByUsuarioAndSenha(String usuario, String senha);


}

