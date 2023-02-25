package br.com.loja.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.api.converter.v1.CategoriaConverter;
import br.com.loja.api.model.v1.input.CategoriaInput;
import br.com.loja.api.model.v1.output.CategoriaOutput;
import br.com.loja.domain.service.CategoriaService;

@RestController
@RequestMapping("/api/v1/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaOutput> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(CategoriaConverter.toOutput(service.findById(id)));
	}
	
	@GetMapping
	public List<CategoriaOutput> findAll() {
		return CategoriaConverter.toOutputList(service.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaOutput create(@RequestBody CategoriaInput input) {
		return CategoriaConverter.toOutput(service.create(CategoriaConverter.toEntity(input)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaOutput> update(@PathVariable("id") Long id, @RequestBody CategoriaInput input) {
		return ResponseEntity.ok(CategoriaConverter.toOutput(service.update(id, CategoriaConverter.toEntity(input))));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}