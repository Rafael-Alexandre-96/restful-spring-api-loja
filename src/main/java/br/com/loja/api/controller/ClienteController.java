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
import br.com.loja.domain.model.Cliente;
import br.com.loja.domain.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/cliente")
@Tag(name = "Cliente", description = "Endpoints para Cliente")
public class ClienteController {
	@Autowired
	private ClienteService service;
	
	@Operation(summary = "Retorna um Cliente através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Cliente encontrado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<ClienteOutput> findById(@Parameter(description = "ID do Cliente [Long]") @PathVariable("id") Long id) {
		return ResponseEntity.ok(ClienteConverter.toOutput(service.findById(id)));
	}
	
	@Operation(summary = "Retorna lista de todos Clientes")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Lista de Clientes", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Cliente.class))) }),
		@ApiResponse(responseCode = "400", description = "Query Param inválido", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping
	public List<ClienteOutput> findAll(@Parameter(description = "Exibir somente registros ativos [boolean]") @RequestParam(value = "onlyActives", defaultValue = "false") boolean onlyActives) {
		if (onlyActives)
			return ClienteConverter.toOutputList(service.findAllActives());
		else
			return ClienteConverter.toOutputList(service.findAll());
	}
	
	@Operation(summary = "Cadastra um novo Cliente via HTTP Body (json)")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = "Cliente criado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
		@ApiResponse(responseCode = "400", description = "Input com dados inválidos", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteOutput create(@RequestBody ClienteInput input) {
		return ClienteConverter.toOutput(service.create(ClienteConverter.toEntity(input)));
	}
	
	@Operation(summary = "Atualiza um Cliente existente via HTTP Body (json)")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Cliente atualizado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
		@ApiResponse(responseCode = "400", description = "Input com dados inválidos", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PutMapping("/{id}")
	public ResponseEntity<ClienteOutput> update(@Parameter(description = "ID do Cliente [Long]") @PathVariable("id") Long id, @RequestBody ClienteInput input) {
		return ResponseEntity.ok(ClienteConverter.toOutput(service.update(id, ClienteConverter.toEntity(input))));
	}
	
	@Operation(summary = "Deleta um Cliente através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = "Deleção confirmada", content = @Content),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido ou Cliente já deletado", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@Parameter(description = "ID do Cliente [Long]") @PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Ativa um Cliente através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = "Ativação confirmada", content = @Content),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido ou Cliente já ativo", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PatchMapping("/{id}/active")
	public ResponseEntity<Void> activeById(@Parameter(description = "ID do Cliente [Long]") @PathVariable("id") Long id) {
		service.activeById(id);
		return ResponseEntity.noContent().build();
	}
}