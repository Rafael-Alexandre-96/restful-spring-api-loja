package br.com.loja.api.model.v1.input;

import java.io.Serializable;

public class SimpleIdInput implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}