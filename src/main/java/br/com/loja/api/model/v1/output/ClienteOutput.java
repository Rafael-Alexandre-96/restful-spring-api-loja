package br.com.loja.api.model.v1.output;

import java.io.Serializable;
import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import br.com.loja.api.model.v1.EnderecoInputOutput;

@JsonPropertyOrder({ "id", "nome", "celular", "email", "endereco", "created_at", "updated_at", "deleted_at" })
public class ClienteOutput implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("id")
	private Long key;
	private String nome;
	private String celular;
	private String email;
	private EnderecoInputOutput endereco;
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
	
	public String getCelular() {
		return celular;
	}
	
	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public EnderecoInputOutput getEndereco() {
		return endereco;
	}
	
	public void setEndereco(EnderecoInputOutput endereco) {
		this.endereco = endereco;
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