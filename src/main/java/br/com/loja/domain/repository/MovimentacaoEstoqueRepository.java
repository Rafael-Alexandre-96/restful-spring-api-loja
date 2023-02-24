package br.com.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.domain.model.MovimentacaoEstoque;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoque, Long> { }