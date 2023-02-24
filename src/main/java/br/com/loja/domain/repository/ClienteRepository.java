package br.com.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}