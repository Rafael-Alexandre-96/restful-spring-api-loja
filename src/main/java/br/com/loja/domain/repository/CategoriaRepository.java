package br.com.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> { }