package com.amine.gestiondestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@SpringBootApplication
@ComponentScan("com.amine.gestiondestock.*")
@ComponentScan({"com.amine.gestiondestock.services.*", "com.amine.gestiondestock.repos","com.amine.gestiondestock.services.servicImplimentation"})
public class GestiondestockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiondestockApplication.class, args);
	}

}
