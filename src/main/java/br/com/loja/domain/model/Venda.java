package br.com.loja.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.com.loja.domain.model.enums.StatusVenda;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_venda")
public class Venda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;
	
	@NotNull
	@Column(name = "data_venda")
	private OffsetDateTime dataVenda;
	
	@Column(name = "data_pagamento")
	private OffsetDateTime dataPagamento;
	
	@NotNull
	@Column(name = "valor_desconto")
	private Double valorDesconto;
	
	@NotNull
	@Column(name = "valor_frete")
	private Double valorFrete;
	
	@OneToMany(mappedBy = "id.venda")
	private List<ItemVenda> itens = new ArrayList<ItemVenda>();
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private StatusVenda status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setNovaVenda() {
		this.setStatus(StatusVenda.PENDENTE);
		this.setDataVenda(OffsetDateTime.now());
	}
	
	public void setFinalizada() {
		this.setStatus(StatusVenda.FINALIZADA);
		this.setDataPagamento(OffsetDateTime.now());
	}
	
	public void setCancelada() {
		this.setStatus(StatusVenda.CANCELADA);
	}
	
	public boolean isPendente() {
		return this.getStatus() == StatusVenda.PENDENTE;
	}
	
	public boolean isFinalizada() {
		return this.getStatus() == StatusVenda.FINALIZADA;
	}
	
	public boolean isCancelada() {
		return this.getStatus() == StatusVenda.CANCELADA;
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

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}
	
	public Double getValorProdutos() {
		Double valor = 0D;
		for(var item : this.getItens()) {
			valor += item.getSubTotal();
		}
		return valor;
	}
	
	public Double getValorTotal() {
		return this.valorDesconto + this.valorFrete + this.getValorProdutos();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		return Objects.equals(id, other.id);
	}
}