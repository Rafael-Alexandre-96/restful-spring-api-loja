package br.com.loja.api.model.v1.output;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "descricao" })
public class CategoriaOutput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("id")
	private Long key;
	private String descricao;
	
	public Long getKey() {
		return key;
	}
	
	public void setKey(Long key) {
		this.key = key;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}