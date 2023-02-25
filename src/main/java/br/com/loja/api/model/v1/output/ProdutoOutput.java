package br.com.loja.api.model.v1.output;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "nome", "valor", "categorias", "created_at", "updated_at", "deleted_at" })
public class ProdutoOutput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long key;
	private String nome;
	private Double valor;
	private List<CategoriaOutput> categorias = new ArrayList<CategoriaOutput>();
	@JsonProperty("created_at")
	private OffsetDateTime createdAt;
	@JsonProperty("updated_at")
	private OffsetDateTime updatedAt;
	@JsonProperty("deleted_at")
	private OffsetDateTime deletedAt;
	
	public Long getKey() {
		return key;
	}
	
	public void setKey(Long key) {
		this.key = key;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Double getValor() {
		return valor;
	}
	
	public void setValor(Double valor) {
		this.valor = valor;
	}
	
	public List<CategoriaOutput> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<CategoriaOutput> categorias) {
		this.categorias = categorias;
	}
	
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public OffsetDateTime getDeletedAt() {
		return deletedAt;
	}
	
	public void setDeletedAt(OffsetDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
}