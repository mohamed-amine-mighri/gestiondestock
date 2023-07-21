package com.amine.gestiondestock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.amine.gestiondestock.services")
public class GestiondestockApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestiondestockApplication.class, args);
	}

}
