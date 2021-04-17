package br.com.api.imobiliaria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class ImobiliariaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImobiliariaApplication.class, args);
	}

}
