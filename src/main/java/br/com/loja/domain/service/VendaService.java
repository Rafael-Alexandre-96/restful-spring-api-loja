package br.com.loja.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja.domain.converter.VendaToMovimentacaoEstoque;
import br.com.loja.domain.exceptions.DomainException;
import br.com.loja.domain.exceptions.EntityNotFoundException;
import br.com.loja.domain.model.Venda;
import br.com.loja.domain.repository.ItemVendaRepository;
import br.com.loja.domain.repository.MovimentacaoEstoqueRepository;
import br.com.loja.domain.repository.VendaRepository;
import jakarta.validation.Valid;

@Service
public class VendaService {
	@Autowired
	private VendaRepository vendaRepository;
	
	@Autowired
	private ItemVendaRepository itemVendaRepository;
	
	@Autowired
	private MovimentacaoEstoqueRepository estoqueRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	public Venda findById(Long id) {
		return vendaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Venda ID %s não encontrada!", id.toString())));
	}
	
	public List<Venda> findAll() {
		return vendaRepository.findAll();
	}
	
	@Transactional
	public Venda generateNewVenda(@Valid Venda input) {
		input.setNovaVenda();
		this.checkItens(input);
		input = vendaRepository.save(input);
		this.updateAndSaveItens(input);
		return this.findById(input.getId());
	}

	@Transactional
	public Venda finalizeVenda(Long id) {
		var finded = this.findById(id);
		if (finded.isPendente())
			finded.setFinalizada();
		else
			throw new DomainException(String.format("Venda ID %s so pode ser finalizada se estiver PENDENTE!", id.toString()));
		this.updateEstoque(finded);
		return vendaRepository.save(finded);
	}
	
	@Transactional
	public Venda cancelVenda(Long id) {
		var finded = this.findById(id);
		var wasFinalizada = finded.isFinalizada();
		if (!finded.isCancelada())
			finded.setCancelada();
		else
			throw new DomainException(String.format("Venda ID %s já está CANCELADA!", id.toString()));
		if (wasFinalizada)
			this.updateEstoque(finded);
		return vendaRepository.save(finded);
	}
	
	private void checkItens(Venda venda) {
		if (venda.getItens().size() == 0)
			throw new DomainException("Venda não possui nenhum produto!");
		
		venda.getItens().forEach(item -> {
			if (item.getQuantidade() <= 0)
				throw new DomainException("Quantidade deve ser maior que 0!");
		});
		
		venda.getItens().forEach(item -> {
			if (item.getProduto() == null)
				throw new DomainException("O produto não pode ser NULO!");
		});
	}
	
	@Transactional
	private void updateAndSaveItens(Venda venda) {
		venda.getItens().forEach(item -> {
			if (item.getValorUnidade() == null) {
				item.setValorUnidade(produtoService.findById(item.getProduto().getId()).getValor());
			}
			
			item.setVenda(venda);
		});
		
		itemVendaRepository.saveAll(venda.getItens());
	}
	
	@Transactional
	private void updateEstoque(Venda input) {
		var movimentacoes = VendaToMovimentacaoEstoque.parseVendaToMovimentacao(input);
		estoqueRepository.saveAll(movimentacoes);
	}
}