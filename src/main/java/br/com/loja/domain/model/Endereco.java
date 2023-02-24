package br.com.loja.domain.model;

import java.io.Serializable;

import br.com.loja.domain.model.enums.UF;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@NotBlank
	@Column(name = "endereco_logradouro")
	@Size(max = 50)
	private String logradouro;
	
	@NotBlank
	@Column(name = "endereco_numero")
	@Size(max = 20)
	private String numero;
	
	@Column(name = "endereco_complemento")
	@Size(max = 20)
	private String complemento;
	
	@NotBlank
	@Column(name = "endereco_bairro")
	@Size(max = 50)
	private String bairro;
	
	@NotBlank
	@Column(name = "endereco_cidade")
	@Size(max = 50)
	private String cidade;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "endereco_uf")
	private UF uf;
	
	public String getLogradouro() {
		return logradouro;
	}
	
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}
	
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	public String getComplemento() {
		return complemento;
	}
	
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public UF getUf() {
		return uf;
	}
	
	public void setUf(UF uf) {
		this.uf = uf;
	}
}