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
import br.com.loja.domain.model.ParcialEstoque;
import br.com.loja.domain.service.MovimentacaoEstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/v1/estoque")
@Tag(name = "Estoque", description = "Endpoints para Movimentacao de Estoque")
public class MovimentacaoEstoqueController {
	@Autowired
	private MovimentacaoEstoqueService service;
	
	@Operation(summary = "Retorna uma Movimentacao através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Movimentacao encontrada", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MovimentacaoEstoqueOutput.class)) }),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<MovimentacaoEstoqueOutput> findById(@Parameter(description = "ID da Movimentacao [Long]") @PathVariable("id") Long id) {
		return ResponseEntity.ok(MovimentacaoEstoqueConverter.toOutput(service.findById(id)));
	}
	
	@Operation(summary = "Retorna lista de todas Movimentacoes")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Lista de Movimentacoes", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MovimentacaoEstoqueOutput.class))) }),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping
	public List<MovimentacaoEstoqueOutput> findAll() {
		return MovimentacaoEstoqueConverter.toOutputList(service.findAll());
	}
	
	@Operation(summary = "Cadastra uma nova Movimentacao via HTTP Body (json)")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = "Movimentacao criada", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MovimentacaoEstoqueOutput.class)) }),
		@ApiResponse(responseCode = "400", description = "Input com dados inválidos", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MovimentacaoEstoqueOutput create(@RequestBody MovimentacaoEstoqueInput input) {
		return MovimentacaoEstoqueConverter.toOutput(service.create(MovimentacaoEstoqueConverter.toEntity(input)));
	}
	
	@Operation(summary = "Atualiza uma Movimentacao existente via HTTP Body (json)")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Movimentacao atualizada", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = MovimentacaoEstoqueOutput.class)) }),
		@ApiResponse(responseCode = "400", description = "Input com dados inválidos", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PutMapping("/{id}")
	public ResponseEntity<MovimentacaoEstoqueOutput> update(@Parameter(description = "ID da Movimentacao [Long]") @PathVariable("id") Long id, @RequestBody MovimentacaoEstoqueInput input) {
		return ResponseEntity.ok(MovimentacaoEstoqueConverter.toOutput(service.update(id, MovimentacaoEstoqueConverter.toEntity(input))));
	}
	
	@Operation(summary = "Deleta uma Movimentacao através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = "Deleção confirmada", content = @Content),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@Parameter(description = "ID da Movimentacao [Long]") @PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(summary = "Retorna lista de produtos no estoque")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Lista de Produtos", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ParcialEstoque.class))) }),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping("/atual")
	public List<ParcialEstoque> getEstoqueAtual() {
		return service.getEstoqueAtual();
	}
	
	@Operation(summary = "Retorna quantidade de um produto no estoque")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Lista de Produtos", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ParcialEstoque.class)) }),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping("/atual/{idProduto}")
	public ParcialEstoque getEstoqueAtualByIdProduto(@Parameter(description = "ID do Produto [Long]") @PathVariable("idProduto") Long idProduto) {
		return service.getEstoqueAtualByIdProduto(idProduto);
	}
}