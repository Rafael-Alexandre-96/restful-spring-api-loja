package br.com.loja.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja.domain.exceptions.EntityNotFoundException;
import br.com.loja.domain.model.MovimentacaoEstoque;
import br.com.loja.domain.repository.MovimentacaoEstoqueRepository;
import jakarta.validation.Valid;

@Service
public class MovimentacaoEstoqueService {
	@Autowired
	private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
	
	public MovimentacaoEstoque findById(Long id) {
		return movimentacaoEstoqueRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("MovimentacaoEstoque ID %s não encontrada!", id.toString())));
	}
	
	public List<MovimentacaoEstoque> findAll() {
		return movimentacaoEstoqueRepository.findAll();
	}
	
	@Transactional
	public MovimentacaoEstoque create(@Valid MovimentacaoEstoque input) {
		input.setDataMovimentacao(OffsetDateTime.now());
		input = movimentacaoEstoqueRepository.save(input);
		return this.findById(input.getId());
	}
	
	@Transactional
	public MovimentacaoEstoque update(Long id, @Valid MovimentacaoEstoque input) {
		var finded = this.findById(id);
		
		finded.setObservacao(input.getObservacao());
		finded.setProduto(input.getProduto());
		finded.setQuantidade(input.getQuantidade());
		finded.setTipoMovimentacao(input.getTipoMovimentacao());
		finded = movimentacaoEstoqueRepository.save(finded);
		
		return this.findById(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		var finded = this.findById(id);
		movimentacaoEstoqueRepository.delete(finded);
	}
}