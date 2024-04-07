package com.teste.attus;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Attus API", version = "1.0", description = "Attus API"))
public class AttusApplication {

	public static void main(String[] args) {
		SpringApplication.run(AttusApplication.class, args);
	}

}
