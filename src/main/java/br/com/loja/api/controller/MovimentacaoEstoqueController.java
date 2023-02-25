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

import br.com.loja.api.converter.v1.MovimentacaoEstoqueConverter;
import br.com.loja.api.model.v1.input.MovimentacaoEstoqueInput;
import br.com.loja.api.model.v1.output.MovimentacaoEstoqueOutput;
import br.com.loja.domain.service.MovimentacaoEstoqueService;

@RestController
@RequestMapping("/api/v1/estoque")
public class MovimentacaoEstoqueController {
	@Autowired
	private MovimentacaoEstoqueService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<MovimentacaoEstoqueOutput> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(MovimentacaoEstoqueConverter.toOutput(service.findById(id)));
	}
	
	@GetMapping
	public List<MovimentacaoEstoqueOutput> findAll() {
		return MovimentacaoEstoqueConverter.toOutputList(service.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MovimentacaoEstoqueOutput create(@RequestBody MovimentacaoEstoqueInput input) {
		return MovimentacaoEstoqueConverter.toOutput(service.create(MovimentacaoEstoqueConverter.toEntity(input)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<MovimentacaoEstoqueOutput> update(@PathVariable("id") Long id, @RequestBody MovimentacaoEstoqueInput input) {
		return ResponseEntity.ok(MovimentacaoEstoqueConverter.toOutput(service.update(id, MovimentacaoEstoqueConverter.toEntity(input))));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}