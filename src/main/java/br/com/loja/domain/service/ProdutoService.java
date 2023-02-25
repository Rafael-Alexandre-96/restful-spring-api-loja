package br.com.loja.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja.domain.exception.DomainException;
import br.com.loja.domain.exception.EntityNotFoundException;
import br.com.loja.domain.model.Produto;
import br.com.loja.domain.repository.ProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EntityManager entityManager;

	public Produto findById(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Produto ID %s não encontrado!", id.toString())));
	}
	
	public List<Produto> findAll() {
		return produtoRepository.findAll();
	}
	
	public List<Produto> findAllActives() {
		return produtoRepository.findAllActives();
	}
	
	@Transactional
	public Produto create(@Valid Produto input) {
		input.setCreatedAt(OffsetDateTime.now());
		input = produtoRepository.saveAndFlush(input);
		entityManager.refresh(input);
		return input;
	}
	
	@Transactional
	public Produto update(Long id, @Valid Produto input) {
		var finded = this.findById(id);
		
		finded.setNome(input.getNome());
		finded.setValor(input.getValor());
		finded.setCategorias(input.getCategorias());
		finded.setUpdatedAt(OffsetDateTime.now());
		finded = produtoRepository.save(finded);
		
		return this.findById(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		var finded = this.findById(id);
		if (finded.isActive()) {
			finded.setDeletedAt(OffsetDateTime.now());
			produtoRepository.save(finded);
		} else {
			throw new DomainException(String.format("Produto ID %s já está deletado!", id.toString()));
		}
	}
	
	@Transactional
	public void activeById(Long id) {
		var finded = this.findById(id);
		if (!finded.isActive()) {
			finded.setDeletedAt(null);
			produtoRepository.save(finded);
		} else {
			throw new DomainException(String.format("Produto ID %s já está ativo!", id.toString()));
		}
	}
}