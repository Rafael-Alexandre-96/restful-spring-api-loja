package br.com.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.domain.model.ParcialEstoque;
import br.com.loja.domain.model.Produto;

public interface ParcialEstoqueRepository extends JpaRepository<ParcialEstoque, String> {
	
	public ParcialEstoque findByProduto(Produto produto);
	
}