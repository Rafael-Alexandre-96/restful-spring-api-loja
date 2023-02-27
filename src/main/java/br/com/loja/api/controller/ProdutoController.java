package br.com.loja.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import br.com.loja.domain.model.Produto;
import br.com.loja.domain.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(
		value = "/api/v1/produto",
		produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
		consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Tag(name = "Produto", description = "Endpoints para Produto")
public class ProdutoController {
	@Autowired
	private ProdutoService service;
	
	@Operation(summary = "Retorna um Produto através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Produto encontrado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<ProdutoOutput> findById(@Parameter(description = "ID do Produto [Long]") @PathVariable("id") Long id) {
		return ResponseEntity.ok(ProdutoConverter.toOutput(service.findById(id)));
	}
	
	@Operation(summary = "Retorna lista de todos Produtos")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Lista de Produtos", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Produto.class))) }),
		@ApiResponse(responseCode = "400", description = "Query Param inválido", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping
	public List<ProdutoOutput> findAll(@Parameter(description = "Exibir somente registros ativos [boolean]") @RequestParam(value = "onlyActives", defaultValue = "false") boolean onlyActives) {
		if (onlyActives)
			return ProdutoConverter.toOutputList(service.findAllActives());
		else
			return ProdutoConverter.toOutputList(service.findAll());
	}
	
	@Operation(summary = "Cadastra um novo Produto via HTTP Body (json)")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = "Produto criado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
		@ApiResponse(responseCode = "400", description = "Input com dados inválidos", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoOutput create(@RequestBody ProdutoInput input) {
		return ProdutoConverter.toOutput(service.create(ProdutoConverter.toEntity(input)));
	}
	
	@Operation(summary = "Atualiza um Produto existente via HTTP Body (json)")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Produto atualizado", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) }),
		@ApiResponse(responseCode = "400", description = "Input com dados inválidos", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoOutput> update(@Parameter(description = "ID do Produto [Long]") @PathVariable("id") Long id, @RequestBody ProdutoInput input) {
		return ResponseEntity.ok(ProdutoConverter.toOutput(service.update(id, ProdutoConverter.toEntity(input))));
	}
	
	@Operation(summary = "Deleta um Produto através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = "Deleção confirmada", content = @Content),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido ou Produto já deletado", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@Parameter(description = "ID do Produto [Long]") @PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Ativa um Produto através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = "Ativação confirmada", content = @Content),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido ou Produto já ativo", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PatchMapping("/{id}/active")
	public ResponseEntity<Void> activeById(@Parameter(description = "ID do Produto [Long]") @PathVariable("id") Long id) {
		service.activeById(id);
		return ResponseEntity.noContent().build();
	}
}