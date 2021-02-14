package com.fezin.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fezin.cursomc.domain.Categoria;
import com.fezin.cursomc.exceptions.ObjectNotFoundException;
import com.fezin.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria find(Long id) {
		Optional<Categoria> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);

		return repository.save(obj);
	}

	public Categoria update(Long id, Categoria obj) {
		Categoria entity = find(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Categoria entity, Categoria obj) {
		entity.setNome(obj.getNome());
	}
}
