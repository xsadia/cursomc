package com.fezin.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fezin.cursomc.domain.Categoria;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaResource {
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		Categoria cat1 = new Categoria(1L, "Cursos");
		Categoria cat2 = new Categoria(2L, "Hardware");
		
		List<Categoria> list = new ArrayList<>();
		list.add(cat1);
		list.add(cat2);
		return ResponseEntity.ok().body(list);
	}
}
