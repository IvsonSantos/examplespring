package com.ivson.cursomc;

import com.ivson.cursomc.domain.Categoria;
import com.ivson.cursomc.domain.Cidade;
import com.ivson.cursomc.domain.Cliente;
import com.ivson.cursomc.domain.Endereco;
import com.ivson.cursomc.domain.Estado;
import com.ivson.cursomc.domain.ItemPedido;
import com.ivson.cursomc.domain.Pagamento;
import com.ivson.cursomc.domain.PagamentoComBoleto;
import com.ivson.cursomc.domain.PagamentoComCartao;
import com.ivson.cursomc.domain.Pedido;
import com.ivson.cursomc.domain.Produto;
import com.ivson.cursomc.domain.enums.EstadoPagamento;
import com.ivson.cursomc.domain.enums.TipoCliente;
import com.ivson.cursomc.repository.CategoriaRepository;
import com.ivson.cursomc.repository.CidadeRepository;
import com.ivson.cursomc.repository.ClienteRepository;
import com.ivson.cursomc.repository.EnderecoRepository;
import com.ivson.cursomc.repository.EstadoRepository;
import com.ivson.cursomc.repository.ItemPedidoRepository;
import com.ivson.cursomc.repository.PagamentoRepository;
import com.ivson.cursomc.repository.PedidoRepository;
import com.ivson.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

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

		Categoria cat1 = new Categoria(null, "Informatica", new ArrayList<>());
		Categoria cat2 = new Categoria(null, "Escritório",  new ArrayList<>());

		Produto p1 = Produto.builder().nome("Computador").preco(2000.00).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
		Produto p2 = Produto.builder().nome("Impressora").preco(800.00).categorias(new ArrayList<>()).itens(new HashSet<>()).build();
		Produto p3 = Produto.builder().nome("Mouse").preco(80.00).categorias(new ArrayList<>()).itens(new HashSet<>()).build();

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado est1 = Estado.builder().nome("Minas Gerais").cidades(new ArrayList<>()).build();
		Estado est2 = Estado.builder().nome("São Paulo").cidades(new ArrayList<>()).build();

		Cidade c1 = Cidade.builder().nome("Uberlandia").estado(est1).build();
		Cidade c2 = Cidade.builder().nome("São Paulo").estado(est2).build();
		Cidade c3 = Cidade.builder().nome("Campinas").estado(est2).build();

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2,c3));

		Cliente cli1 = new Cliente(null, "Maria Silva","maria@gmail.com","363789122377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "9383"));

		Endereco e1 = Endereco.builder().numero("300").logradouro("rua flores").complemento("apto 303").cep("38220834")
				.bairro("jardim").cliente(cli1).cidade(c1).build();
		Endereco e2 = Endereco.builder().numero("105").logradouro("Avenida Matos").complemento("sala 8").cep("38220834")
				.bairro("jardim").cliente(cli1).cidade(c2).build();

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = Pedido.builder().instante(sdf.parse("30/09/2017 10:32"))
				.cliente(cli1).enderecoDeEntrega(e1).build();
		Pedido ped2 = Pedido.builder().instante(sdf.parse("10/10/2017 19:35"))
				.cliente(cli1).enderecoDeEntrega(e2).build();

		Pagamento pagto1 = new PagamentoComCartao(6);
		pagto1.setEstado(EstadoPagamento.QUITADO);
		pagto1.setPedido(ped1);
		ped1.setPagamento(pagto1);
		ped1.setItens(new HashSet<>());

		Pagamento pagto2 = new PagamentoComBoleto(null, sdf.parse("20/10/2017 00:00"));
		pagto2.setEstado(EstadoPagamento.PENDENTE);
		pagto2.setPedido(ped2);
		ped2.setPagamento(pagto2);
		ped2.setItens(new HashSet<>());

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 2, 80.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
