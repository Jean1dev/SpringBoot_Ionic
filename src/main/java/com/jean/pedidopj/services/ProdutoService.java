package com.jean.pedidopj.services;
import java.util.List;
import java.util.Optional;

import com.jean.pedidopj.domain.Categoria;
import com.jean.pedidopj.domain.Produto;
import com.jean.pedidopj.exceptions.ObjectNotFoundException;
import com.jean.pedidopj.repositories.CategoriaRepository;
import com.jean.pedidopj.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
 public class ProdutoService {
 
  	@Autowired
 	private ProdutoRepository repo;
 
  	@Autowired
 	private CategoriaRepository categoriaRepository;
 
  	public Produto find(Integer id) {
 		Optional<Produto> obj = repo.findById(id);
 		return obj.orElseThrow(() -> new ObjectNotFoundException(
 				"Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
 	}
 
  	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
 		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
 		List<Categoria> categorias = categoriaRepository.findAllById(ids);
 		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);	
 	}
 }