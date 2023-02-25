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

import br.com.loja.api.converter.v1.ClienteConverter;
import br.com.loja.api.model.v1.input.ClienteInput;
import br.com.loja.api.model.v1.output.ClienteOutput;
import br.com.loja.domain.service.ClienteService;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteOutput> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(ClienteConverter.toOutput(service.findById(id)));
	}
	
	@GetMapping
	public List<ClienteOutput> findAll(@RequestParam(value = "onlyActives", defaultValue = "false") boolean onlyActives) {
		if (onlyActives)
			return ClienteConverter.toOutputList(service.findAllActives());
		else
			return ClienteConverter.toOutputList(service.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteOutput create(@RequestBody ClienteInput input) {
		return ClienteConverter.toOutput(service.create(ClienteConverter.toEntity(input)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteOutput> update(@PathVariable("id") Long id, @RequestBody ClienteInput input) {
		return ResponseEntity.ok(ClienteConverter.toOutput(service.update(id, ClienteConverter.toEntity(input))));
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