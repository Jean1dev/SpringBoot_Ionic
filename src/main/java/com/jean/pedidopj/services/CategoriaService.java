package com.jean.pedidopj.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jean.pedidopj.domain.Categoria;
import com.jean.pedidopj.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(int id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.get();
	}
}
