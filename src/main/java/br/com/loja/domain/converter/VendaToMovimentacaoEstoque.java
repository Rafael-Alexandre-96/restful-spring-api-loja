package br.com.loja.domain.converter;

import java.util.ArrayList;
import java.util.List;

import br.com.loja.domain.model.MovimentacaoEstoque;
import br.com.loja.domain.model.Venda;

public class VendaToMovimentacaoEstoque {
	
	public static List<MovimentacaoEstoque> parseVendaToMovimentacao(Venda venda) {
		List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();
		
		venda.getItens().forEach(item -> {
			MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
			movimentacao.setProduto(item.getProduto());
			movimentacao.setQuantidade(item.getQuantidade());
			movimentacao.setDataMovimentacao(venda.getDataVenda());
			
			if (venda.isFinalizada()) {
				movimentacao.setTipoSaida();
				movimentacao.setObservacao(String.format("Gerado automaticamente pela finalização da Venda ID %s!", venda.getId()));
			} else if (venda.isCancelada()) {
				movimentacao.setTipoEntrada();
				movimentacao.setObservacao(String.format("Gerado automaticamente pelo cancelamento da Venda ID %s!", venda.getId()));
			}
			
			movimentacoes.add(movimentacao);
		});
		
		return movimentacoes;
	}
}