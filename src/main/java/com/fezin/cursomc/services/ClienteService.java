package com.fezin.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fezin.cursomc.domain.Cidade;
import com.fezin.cursomc.domain.Cliente;
import com.fezin.cursomc.domain.Endereco;
import com.fezin.cursomc.domain.enums.TipoCliente;
import com.fezin.cursomc.dto.ClienteDTO;
import com.fezin.cursomc.dto.ClienteNewDTO;
import com.fezin.cursomc.repositories.ClienteRepository;
import com.fezin.cursomc.repositories.EnderecoRepository;
import com.fezin.cursomc.services.exceptions.DataIntegrityException;
import com.fezin.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Transactional
	public Cliente insert(Cliente obj) {
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		
		return obj;
	}
	
	public Cliente find(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return repository.findAll(pageRequest);
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		}
		
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que possui entidades relacionadas."); 
		}
		
	}

	public Cliente update(Long id, Cliente obj) {
		Cliente entity = find(id);
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(Cliente entity, Cliente obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
	}
	
	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		
		if(objDTO.getTelefone2() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		
		return cli;
	}
}
