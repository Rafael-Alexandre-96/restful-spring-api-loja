package br.com.loja.api.model.v1.output;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "produto", "quantidade", "valor_unidade" })
public class ItemVendaOutput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ProdutoOutput produto;
	private Integer quantidade;
	@JsonProperty("valor_unidade")
	private Double valorUnidade;
	private Double subTotal;
	
	public void setProduto(ProdutoOutput produto) {
		this.produto = produto;
	}
	
	public ProdutoOutput getProduto() {
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

	public Double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(Double subTotal) {
		this.subTotal = subTotal;
	}
}