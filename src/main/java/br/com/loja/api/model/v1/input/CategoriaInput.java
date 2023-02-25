package br.com.loja.api.model.v1.input;

import java.io.Serializable;

public class CategoriaInput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}