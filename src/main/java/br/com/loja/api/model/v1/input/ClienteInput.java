package br.com.loja.api.model.v1.input;

import java.io.Serializable;

import br.com.loja.api.model.v1.EnderecoInputOutput;

public class ClienteInput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String celular;
	private String email;
	private EnderecoInputOutput endereco;
	
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
}