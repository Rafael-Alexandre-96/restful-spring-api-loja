package br.com.loja.api.model.v1.input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VendaInput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private SimpleIdInput cliente;
	@JsonProperty("valor_desconto")
	private Double valorDesconto;
	@JsonProperty("valor_frete")
	private Double valorFrete;
	private List<ItemVendaInput> itens = new ArrayList<ItemVendaInput>();

	public SimpleIdInput getCliente() {
		return cliente;
	}

	public void setCliente(SimpleIdInput cliente) {
		this.cliente = cliente;
	}

	public Double getValorDesconto() {
		return valorDesconto;
	}

	public void setValorDesconto(Double valorDesconto) {
		this.valorDesconto = valorDesconto;
	}

	public Double getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(Double valorFrete) {
		this.valorFrete = valorFrete;
	}

	public List<ItemVendaInput> getItens() {
		return itens;
	}

	public void setItens(List<ItemVendaInput> itens) {
		this.itens = itens;
	}
}