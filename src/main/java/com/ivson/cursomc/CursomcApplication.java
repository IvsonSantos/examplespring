package com.ivson.cursomc;

import com.ivson.cursomc.domain.Categoria;
import com.ivson.cursomc.domain.Produto;
import com.ivson.cursomc.repository.CategoriaRepository;
import com.ivson.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica", new ArrayList<>());
		Categoria cat2 = new Categoria(null, "Escritório",  new ArrayList<>());

		Produto p1 = Produto.builder().nome("Computador").preco(2000.00).categorias(new ArrayList<>()).build();
		Produto p2 = Produto.builder().nome("Impressora").preco(800.00).categorias(new ArrayList<>()).build();
		Produto p3 = Produto.builder().nome("Mouse").preco(80.00).categorias(new ArrayList<>()).build();

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
