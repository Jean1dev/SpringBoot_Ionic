package com.jean.pedidopj.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jean.pedidopj.domain.*;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
