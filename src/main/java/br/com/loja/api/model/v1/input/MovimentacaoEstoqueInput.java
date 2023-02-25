package br.com.loja.api.model.v1.input;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.loja.domain.model.enums.TipoMovimentacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class MovimentacaoEstoqueInput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private SimpleIdInput produto;
	private Integer quantidade;
	@Enumerated(EnumType.STRING)
	@JsonProperty("tipo_movimentacao")
	private TipoMovimentacao tipoMovimentacao;
	private String observacao;

	public SimpleIdInput getProduto() {
		return produto;
	}

	public void setProduto(SimpleIdInput produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}