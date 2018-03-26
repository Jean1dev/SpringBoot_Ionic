package com.jean.pedidopj.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jean.pedidopj.domain.Categoria;
import com.jean.pedidopj.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable int id) {
		
		Categoria body = service.buscar(id);
		
		return ResponseEntity.ok().body(body);
		
		/*Categoria cat1 = new Categoria(1, "oi");
		Categoria cat2 = new Categoria(2, "oiwww");
		
		List<Categoria> list = new ArrayList<>();
		list.add(cat1);
		list.add(cat2);
		
		
		return list;*/
	}
}
