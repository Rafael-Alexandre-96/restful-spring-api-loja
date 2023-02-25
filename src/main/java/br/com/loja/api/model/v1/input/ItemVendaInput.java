package br.com.loja.api.model.v1.input;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemVendaInput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private SimpleIdInput produto;
	private Integer quantidade;
	@JsonProperty("valor_unidade")
	private Double valorUnidade;
	
	public void setProduto(SimpleIdInput produto) {
		this.produto = produto;
	}
	
	public SimpleIdInput getProduto() {
		return this.produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorUnidade() {
		return valorUnidade;
	}

	public void setValorUnidade(Double valorUnidade) {
		this.valorUnidade = valorUnidade;
	}
}