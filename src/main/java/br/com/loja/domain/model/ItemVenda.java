package br.com.loja.domain.model;

import java.io.Serializable;
import java.util.Objects;

import br.com.loja.domain.model.pk.ItemVendaPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_item_venda")
public class ItemVenda implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ItemVendaPK id = new ItemVendaPK();
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private Double valorUnidade;
	
	public void setVenda(Venda venda) {
		this.id.setVenda(venda);
	}
	
	public Venda getVenda() {
		return this.id.getVenda();
	}
	
	public void setProduto(Produto produto) {
		this.id.setProduto(produto);
	}
	
	public Produto getProduto() {
		return this.id.getProduto();
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
		ItemVenda other = (ItemVenda) obj;
		return Objects.equals(id, other.id);
	}
}