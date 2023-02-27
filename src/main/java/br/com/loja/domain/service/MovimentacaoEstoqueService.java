package br.com.loja.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja.domain.exception.DomainException;
import br.com.loja.domain.exception.EntityNotFoundException;
import br.com.loja.domain.model.MovimentacaoEstoque;
import br.com.loja.domain.model.ParcialEstoque;
import br.com.loja.domain.repository.MovimentacaoEstoqueRepository;
import br.com.loja.domain.repository.ParcialEstoqueRepository;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;

@Service
public class MovimentacaoEstoqueService {
	@Autowired
	private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
	
	@Autowired
	private ParcialEstoqueRepository parcialEstoqueRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private EntityManager entityManager;
	
	public MovimentacaoEstoque findById(Long id) {
		return movimentacaoEstoqueRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("MovimentacaoEstoque ID %s não encontrada!", id.toString())));
	}
	
	public List<MovimentacaoEstoque> findAll() {
		return movimentacaoEstoqueRepository.findAll();
	}
	
	@Transactional
	public MovimentacaoEstoque create(@Valid MovimentacaoEstoque input) {
		input.setDataMovimentacao(OffsetDateTime.now());
		this.checkQuantidade(input);
		
		input = movimentacaoEstoqueRepository.saveAndFlush(input);
		entityManager.refresh(input);
		return input;
	}
	
	@Transactional
	public MovimentacaoEstoque update(Long id, @Valid MovimentacaoEstoque input) {
		var finded = this.findById(id);
		
		finded.setObservacao(input.getObservacao());
		finded.setProduto(input.getProduto());
		finded.setQuantidade(input.getQuantidade());
		finded.setTipoMovimentacao(input.getTipoMovimentacao());
		
		this.checkQuantidade(input);
		
		input = movimentacaoEstoqueRepository.saveAndFlush(input);
		entityManager.refresh(input);
		return input;
	}
	
	@Transactional
	public void deleteById(Long id) {
		var finded = this.findById(id);
		movimentacaoEstoqueRepository.delete(finded);
	}
	
	public List<ParcialEstoque> getEstoqueAtual() {
		return parcialEstoqueRepository.findAll();
	}
	
	public ParcialEstoque getEstoqueAtualByIdProduto(Long idProduto) {
		var produto = produtoService.findById(idProduto);
		return parcialEstoqueRepository.findByProduto(produto);
	}
	
	private void checkQuantidade(MovimentacaoEstoque movimentacao) {	
		if (movimentacao.getQuantidade() == null || movimentacao.getQuantidade() <= 0)
			throw new DomainException("Quantidade deve ser maior que 0!");
	}
}