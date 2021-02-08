package com.fezin.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fezin.cursomc.domain.Categoria;
import com.fezin.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaResource {
	@Autowired
	private CategoriaService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Long id) {
		
		Categoria obj = service.buscar(id);
		
		return ResponseEntity.ok().body(obj);
	}
}
