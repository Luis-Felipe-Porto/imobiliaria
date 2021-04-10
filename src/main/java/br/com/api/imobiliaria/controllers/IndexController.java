package br.com.api.imobiliaria.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	@GetMapping
	public String imobiliaria() {
		return "BEM VINDO A API DE IMOBILIARIA";
	}
}
