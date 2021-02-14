package com.fezin.cursomc.dto;

import java.io.Serializable;

import com.fezin.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria obj) {
		this.nome = obj.getNome();
		this.id = obj.getId();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
