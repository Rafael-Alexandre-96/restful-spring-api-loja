package br.com.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> { }