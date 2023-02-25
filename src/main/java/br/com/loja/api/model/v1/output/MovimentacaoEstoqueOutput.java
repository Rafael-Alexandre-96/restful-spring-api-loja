package br.com.loja.api.model.v1.output;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.loja.domain.model.enums.TipoMovimentacao;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@JsonPropertyOrder({ "id", "produto", "data_movimentacao", "quantidade", "tipo_movimentacao", "observacao" })
public class MovimentacaoEstoqueOutput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long key;
	private ProdutoOutput produto;
	@JsonProperty("data_movimentacao")
	private OffsetDateTime dataMovimentacao;
	private Integer quantidade;
	@Enumerated(EnumType.STRING)
	@JsonProperty("tipo_movimentacao")
	private TipoMovimentacao tipoMovimentacao;
	private String observacao;

	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}
	
	public ProdutoOutput getProduto() {
		return produto;
	}

	public void setProduto(ProdutoOutput produto) {
		this.produto = produto;
	}

	public OffsetDateTime getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(OffsetDateTime dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
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
	
	public void setTipoEntrada() {
		this.tipoMovimentacao = TipoMovimentacao.ENTRADA;
	}
	
	public void setTipoSaida() {
		this.tipoMovimentacao = TipoMovimentacao.SAIDA;
	}
	
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
}