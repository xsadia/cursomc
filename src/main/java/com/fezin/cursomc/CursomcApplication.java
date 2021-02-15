package com.fezin.cursomc;

import java.text.SimpleDateFormat;
import java.time.Instant;
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
import com.fezin.cursomc.domain.ItemPedido;
import com.fezin.cursomc.domain.Pagamento;
import com.fezin.cursomc.domain.PagamentoComBoleto;
import com.fezin.cursomc.domain.PagamentoComCartao;
import com.fezin.cursomc.domain.Pedido;
import com.fezin.cursomc.domain.Produto;
import com.fezin.cursomc.domain.enums.EstadoPagamento;
import com.fezin.cursomc.domain.enums.TipoCliente;
import com.fezin.cursomc.repositories.CategoriaRepository;
import com.fezin.cursomc.repositories.CidadeRepository;
import com.fezin.cursomc.repositories.ClienteRepository;
import com.fezin.cursomc.repositories.EnderecoRepository;
import com.fezin.cursomc.repositories.EstadoRepository;
import com.fezin.cursomc.repositories.ItemPedidoRepository;
import com.fezin.cursomc.repositories.PagamentoRepository;
import com.fezin.cursomc.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Perfumaria");
		Categoria cat4 = new Categoria(null, "Jardinagem");
		Categoria cat5 = new Categoria(null, "Cozinha");
		Categoria cat6 = new Categoria(null, "Banho");
		Categoria cat7 = new Categoria(null, "Decoração");
		
		Produto p1 = new Produto(null, "computador", 2000.0);
		Produto p2 = new Produto(null, "impressora", 800.0);
		Produto p3 = new Produto(null, "mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, Instant.now(), cli1, e1);
		
		Pedido ped2 = new Pedido(null, Instant.now(), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p1.getItens().addAll(Arrays.asList(ip3));
		p1.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
