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
import br.com.loja.domain.model.Categoria;
import br.com.loja.domain.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categoria")
@Tag(name = "Categoria", description = "Endpoints para Categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService service;
	
	@Operation(summary = "Retorna uma Categoria através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Categoria encontrada", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaOutput> findById(@Parameter(description = "ID da Categoria [Long]") @PathVariable("id") Long id) {
		return ResponseEntity.ok(CategoriaConverter.toOutput(service.findById(id)));
	}
	
	@Operation(summary = "Retorna lista de todas Categorias")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Lista de Categorias", content = { @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Categoria.class))) }),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@GetMapping
	public List<CategoriaOutput> findAll() {
		return CategoriaConverter.toOutputList(service.findAll());
	}
	
	@Operation(summary = "Cadastra uma nova Categoria via HTTP Body (json)")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "201", description = "Categoria criada", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
		@ApiResponse(responseCode = "400", description = "Input com dados inválidos", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content), 
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CategoriaOutput create(@RequestBody CategoriaInput input) {
		return CategoriaConverter.toOutput(service.create(CategoriaConverter.toEntity(input)));
	}
	
	@Operation(summary = "Atualiza uma Categoria existente via HTTP Body (json)")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Categoria atualizada", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Categoria.class)) }),
		@ApiResponse(responseCode = "400", description = "Input com dados inválidos", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaOutput> update(@Parameter(description = "ID da Categoria [Long]") @PathVariable("id") Long id, @RequestBody CategoriaInput input) {
		return ResponseEntity.ok(CategoriaConverter.toOutput(service.update(id, CategoriaConverter.toEntity(input))));
	}
	
	@Operation(summary = "Deleta uma Categoria através do ID")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "204", description = "Deleção confirmada", content = @Content),
		@ApiResponse(responseCode = "400", description = "Paramentro ID inválido", content = @Content),
		@ApiResponse(responseCode = "403", description = "Não autorizado", content = @Content),
		@ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content),
		@ApiResponse(responseCode = "500", description = "Erro no servidor", content = @Content)
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@Parameter(description = "ID da Categoria [Long]") @PathVariable("id") Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}