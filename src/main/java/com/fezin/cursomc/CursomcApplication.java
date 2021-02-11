package com.fezin.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fezin.cursomc.domain.Categoria;
import com.fezin.cursomc.domain.Cidade;
import com.fezin.cursomc.domain.Cliente;
import com.fezin.cursomc.domain.Endereco;
import com.fezin.cursomc.domain.Estado;
import com.fezin.cursomc.domain.Produto;
import com.fezin.cursomc.domain.enums.TipoCliente;
import com.fezin.cursomc.repositories.CategoriaRepository;
import com.fezin.cursomc.repositories.CidadeRepository;
import com.fezin.cursomc.repositories.ClienteRepository;
import com.fezin.cursomc.repositories.EnderecoRepository;
import com.fezin.cursomc.repositories.EstadoRepository;
import com.fezin.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "computador", 2000.0);
		Produto p2 = new Produto(null, "impressora", 800.0);
		Produto p3 = new Produto(null, "mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Santa Catarina");
		
		Cidade c1 = new Cidade(null, "Florianópolis", est2);	
		Cidade c2 = new Cidade(null, "São Paulo", est1);
		Cidade c3 = new Cidade(null, "Campinas", est1);
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "12455656234", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("999922499", "9977288"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "APTO 203", "Jardim", "2200010", cli1, c1);
		
		Endereco e2 = new Endereco(null, "Avenida matos", "105", "sala 800", "Centro", "3897870", cli1, c2);
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}

}
