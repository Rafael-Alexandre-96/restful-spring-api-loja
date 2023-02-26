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
import br.com.loja.domain.model.Venda;
import br.com.loja.domain.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/venda")
@Tag(name = "Venda", description = "Endpoints para Venda")
public class VendaController {
	@Autowired
	private VendaService service;
	
	@Operation(summary = "Retorna uma Venda através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Venda encontrada", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Venda.class)) }),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<VendaOutput> findById(@Parameter(description = "ID da Venda [Long]") @PathVariable("id") Long id) {
		return ResponseEntity.ok(VendaConverter.toOutput(service.findById(id)));
	}
	
	@Operation(summary = "Retorna lista de todas Vendas")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Lista de Vendas", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Venda.class))) }),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping
	public List<VendaOutput> findAll() {
		return VendaConverter.toOutputList(service.findAll());
	}
	
	@Operation(summary = "Cadastra uma nova Venda via HTTP Body (json)")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = "Venda criada", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Venda.class)) }),
		@ApiResponse(responseCode = "400", description = "Input com dados inválidos", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public VendaOutput generateNewVenda(@RequestBody VendaInput input) {
		return VendaConverter.toOutput(service.generateNewVenda(VendaConverter.toEntity(input)));
	}
	
	@Operation(summary = "Finaliza uma Venda através do ID e atualiza estoque")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = "Finalizacao confirmada", content = @Content),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido ou Venda impossivel de finalizar", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PatchMapping("/{id}/finalizar")
	public ResponseEntity<Void> finalizeVenda(@Parameter(description = "ID da Venda [Long]")  @PathVariable("id") Long id) {
		service.finalizeVenda(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Cancela uma Venda através do ID e atualiza estoque")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = "Cancelamento confirmado", content = @Content),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido ou Venda impossivel de cancelar", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PatchMapping("/{id}/cancelar")
	public ResponseEntity<Void> cancelVenda(@Parameter(description = "ID da Venda [Long]")  @PathVariable("id") Long id) {
		service.cancelVenda(id);
		return ResponseEntity.noContent().build();
	}
}