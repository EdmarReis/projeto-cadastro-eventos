package com.edmar.cadastro.interfaces.controller;

import com.edmar.cadastro.application.usecase.LoginUseCaseImpl;
import com.edmar.cadastro.interfaces.dto.login.LoginDto;
import com.edmar.cadastro.interfaces.dto.login.LoginResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginUseCaseImpl loginUseCase;

    @PostMapping
    public ResponseEntity<Object> recebeLogin(@RequestBody LoginDto login){
        try{
            if(loginUseCase.buscarUsuario(login.login(), login.senha())){
                //System.out.println("Logado com sucesso. "+login.login()+" "+login.senha());
                LoginResponseDto responseDto = new LoginResponseDto(
                        "Usuario "+login.login()+" logado com sucesso.",
                        HttpStatus.OK.value()
                );
                return ResponseEntity.ok(responseDto);
            }else {
                //System.out.println("Usuario Nao encontrado. "+login.login()+" "+login.senha());
                LoginResponseDto responseDto = new LoginResponseDto(
                        "Login e/ou senha invalidos.",
                        HttpStatus.NOT_FOUND.value()
                );
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(responseDto);
            }
        }catch (Exception e){
            //System.out.println("Erro. "+login.login()+" "+login.senha()+" "+e);
            LoginResponseDto responseDto = new LoginResponseDto(
                    "Erro: "+e,
                    HttpStatus.INTERNAL_SERVER_ERROR.value()
            );
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(responseDto);
        }
    }
}
