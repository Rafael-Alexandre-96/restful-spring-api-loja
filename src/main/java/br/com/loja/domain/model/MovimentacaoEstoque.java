package br.com.loja.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import br.com.loja.domain.model.enums.TipoMovimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_movimentacao_estoque")
public class MovimentacaoEstoque implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	
	@NotNull
	@Column(name = "data_movimentacao")
	private OffsetDateTime dataMovimentacao;
	
	@NotNull
	private Integer quantidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_movimentacao")
	private TipoMovimentacao tipoMovimentacao;
	
	@Size(max = 255)
	private String observacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
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
		MovimentacaoEstoque other = (MovimentacaoEstoque) obj;
		return Objects.equals(id, other.id);
	}
}