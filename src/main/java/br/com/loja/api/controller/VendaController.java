package br.com.loja.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.api.converter.v1.VendaConverter;
import br.com.loja.api.model.v1.input.VendaInput;
import br.com.loja.api.model.v1.output.VendaOutput;
import br.com.loja.domain.service.VendaService;

@RestController
@RequestMapping("/api/v1/venda")
public class VendaController {
	@Autowired
	private VendaService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<VendaOutput> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(VendaConverter.toOutput(service.findById(id)));
	}
	
	@GetMapping
	public List<VendaOutput> findAll() {
		return VendaConverter.toOutputList(service.findAll());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendaOutput generateNewVenda(@RequestBody VendaInput input) {
		return VendaConverter.toOutput(service.generateNewVenda(VendaConverter.toEntity(input)));
	}
	
	@PatchMapping("/{id}/finalizar")
	public ResponseEntity<Void> finalizeVenda(@PathVariable("id") Long id) {
		service.finalizeVenda(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping("/{id}/cancelar")
	public ResponseEntity<Void> cancelVenda(@PathVariable("id") Long id) {
		service.cancelVenda(id);
		return ResponseEntity.noContent().build();
	}
}