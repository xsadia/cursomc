package com.fezin.cursomc.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaResource {
	
	@GetMapping
	public String listar() {
		return "REST Funcionando";
	}
}
