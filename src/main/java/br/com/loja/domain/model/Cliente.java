package br.com.loja.domain.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import br.com.loja.domain.model.contract.RegistryTimestamps;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable, RegistryTimestamps {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 50)
	private String nome;
	
	@NotNull
	@Size(max = 20)
	private String celular;
	
	@Size(max = 40)
	private String email;
	
	@Embedded
	@NotNull
	@Valid
	private Endereco endereco;
	
	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	private OffsetDateTime createdAt;
	
	@LastModifiedDate
	@Column(name = "updated_at")
	private OffsetDateTime updatedAt;
	
	@Column(name = "deleted_at")
	private OffsetDateTime deletedAt;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
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
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	@Override
	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public OffsetDateTime getUpdatedAt() {
		return updatedAt;
	}
	
	@Override
	public void setUpdatedAt(OffsetDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Override
	public OffsetDateTime getDeletedAt() {
		return deletedAt;
	}
	
	@Override
	public void setDeletedAt(OffsetDateTime deletedAt) {
		this.deletedAt = deletedAt;
	}
	
	@Override
	public boolean isActive() {
		return this.getDeletedAt() == null;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
}