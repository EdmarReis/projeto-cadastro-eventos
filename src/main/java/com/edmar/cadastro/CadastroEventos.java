package com.edmar.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(scanBasePackages = "com.edmar.cadastro")
public class CadastroEventos {

	public static void main(String[] args) {
		SpringApplication.run(CadastroEventos.class, args);
	}

}
