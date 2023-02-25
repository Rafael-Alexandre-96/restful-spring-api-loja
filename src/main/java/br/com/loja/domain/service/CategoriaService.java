package br.com.loja.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja.domain.exception.EntityNotFoundException;
import br.com.loja.domain.model.Categoria;
import br.com.loja.domain.repository.CategoriaRepository;
import jakarta.validation.Valid;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Categoria findById(Long id) {
		return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Categoria ID %s não encontrada!", id.toString())));
	}
	
	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	@Transactional
	public Categoria create(@Valid Categoria input) {
		input = categoriaRepository.save(input);
		return this.findById(input.getId());
	}
	
	@Transactional
	public Categoria update(Long id, @Valid Categoria input) {
		var finded = this.findById(id);
		
		finded.setDescricao(input.getDescricao());
		finded = categoriaRepository.save(finded);
		
		return this.findById(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		var finded = this.findById(id);
		categoriaRepository.delete(finded);
	}
}