package br.com.loja.api.model.v1.input;

import java.io.Serializable;
import java.util.List;

public class ProdutoInput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Double valor;
	private List<SimpleIdInput> categorias;
	
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
	
	public List<SimpleIdInput> getCategorias() {
		return categorias;
	}
	
	public void setCategorias(List<SimpleIdInput> categorias) {
		this.categorias = categorias;
	}
}