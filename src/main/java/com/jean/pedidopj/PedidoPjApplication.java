package com.jean.pedidopj;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jean.pedidopj.domain.Categoria;
import com.jean.pedidopj.domain.Produto;
import com.jean.pedidopj.repositories.CategoriaRepository;
import com.jean.pedidopj.repositories.ProdutoRepository;

@SpringBootApplication
public class PedidoPjApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repo;
	@Autowired
	private ProdutoRepository repoPro;
	
	public static void main(String[] args) {
		SpringApplication.run(PedidoPjApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cate1 = new Categoria(null, "informatica");
		Categoria cate2 = new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null, "pc", 2000.00);
		Produto p2 = new Produto(null, "impre", 150.50);
		Produto p3 = new Produto(null, "mouse", 90.97);
		
		cate1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cate2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cate1));
		p2.getCategorias().addAll(Arrays.asList(cate1, cate2));
		p3.getCategorias().addAll(Arrays.asList(cate1));
		
		repo.saveAll(Arrays.asList(cate1, cate2));
		repoPro.saveAll(Arrays.asList(p1, p2, p3));
	}
}
