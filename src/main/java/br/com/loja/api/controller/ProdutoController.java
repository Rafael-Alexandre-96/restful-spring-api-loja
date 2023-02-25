package br.com.loja.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.api.converter.v1.ProdutoConverter;
import br.com.loja.api.model.v1.input.ProdutoInput;
import br.com.loja.api.model.v1.output.ProdutoOutput;
import br.com.loja.domain.service.ProdutoService;

@RestController
@RequestMapping("/api/v1/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoOutput> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(ProdutoConverter.toOutput(service.findById(id)));
	}
	
	@GetMapping
	public List<ProdutoOutput> findAll(@RequestParam(value = "onlyActives", defaultValue = "false") boolean onlyActives) {
		if (onlyActives)
			return ProdutoConverter.toOutputList(service.findAllActives());
		else
			return ProdutoConverter.toOutputList(service.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoOutput create(@RequestBody ProdutoInput input) {
		return ProdutoConverter.toOutput(service.create(ProdutoConverter.toEntity(input)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoOutput> update(@PathVariable("id") Long id, @RequestBody ProdutoInput input) {
		return ResponseEntity.ok(ProdutoConverter.toOutput(service.update(id, ProdutoConverter.toEntity(input))));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/active")
	public ResponseEntity<Void> activeById(@PathVariable("id") Long id) {
		service.activeById(id);
		return ResponseEntity.noContent().build();
	}
}