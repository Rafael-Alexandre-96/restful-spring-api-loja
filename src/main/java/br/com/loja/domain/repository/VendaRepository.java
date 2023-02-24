package br.com.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.domain.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> { }