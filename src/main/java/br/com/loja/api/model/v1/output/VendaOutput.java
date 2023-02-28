package br.com.loja.api.model.v1.output;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.loja.domain.model.enums.StatusVenda;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@JsonPropertyOrder({ "id", "cliente", "status", "data_venda", "data_pagamento", "valor_produtos", "valor_desconto", "valor_frete", "valor_total", "itens" })
public class VendaOutput extends RepresentationModel<VendaOutput> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long key;
	private ClienteOutput cliente;
	@JsonProperty("data_venda")
	private OffsetDateTime dataVenda;
	@JsonProperty( "data_pagamento")
	private OffsetDateTime dataPagamento;
	@JsonProperty("valor_desconto")
	private Double valorDesconto;
	@JsonProperty("valor_frete")
	private Double valorFrete;
	private List<ItemVendaOutput> itens = new ArrayList<ItemVendaOutput>();
	@Enumerated(EnumType.STRING)
	private StatusVenda status;
	@JsonProperty("valor_produtos")
	private Double valorProdutos;
	@JsonProperty("valor_total")
	private Double valorTotal;
	
	public Long getKey() {
		return key;
	}

	public void setKey(Long key) {
		this.key = key;
	}

	public ClienteOutput getCliente() {
		return cliente;
	}

	public void setCliente(ClienteOutput cliente) {
		this.cliente = cliente;
	}

	public OffsetDateTime getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(OffsetDateTime dataVenda) {
		this.dataVenda = dataVenda;
	}

	public OffsetDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(OffsetDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
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

	public List<ItemVendaOutput> getItens() {
		return itens;
	}

	public void setItens(List<ItemVendaOutput> itens) {
		this.itens = itens;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}

	public Double getValorProdutos() {
		return valorProdutos;
	}

	public void setValorProdutos(Double valorProdutos) {
		this.valorProdutos = valorProdutos;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
}