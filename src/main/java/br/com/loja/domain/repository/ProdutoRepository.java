package br.com.loja.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.loja.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	@Query("SELECT p FROM Produto p WHERE p.deletedAt = null")
	List<Produto> findAllActives();
	
}