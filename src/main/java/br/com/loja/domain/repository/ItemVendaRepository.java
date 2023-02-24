package br.com.loja.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.domain.model.ItemVenda;
import br.com.loja.domain.model.pk.ItemVendaPK;

public interface ItemVendaRepository extends JpaRepository<ItemVenda, ItemVendaPK> { }