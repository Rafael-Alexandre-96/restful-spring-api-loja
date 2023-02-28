package br.com.loja.api.converter.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.loja.api.controller.MovimentacaoEstoqueController;
import br.com.loja.api.model.v1.input.MovimentacaoEstoqueInput;
import br.com.loja.api.model.v1.output.MovimentacaoEstoqueOutput;
import br.com.loja.domain.model.MovimentacaoEstoque;

public class MovimentacaoEstoqueConverter {
	static ModelMapper mapper = new ModelMapper();
	
	public static MovimentacaoEstoque toEntity(MovimentacaoEstoqueInput input) {
		mapper.typeMap(MovimentacaoEstoqueInput.class, MovimentacaoEstoque.class).addMappings(mapper -> {
			mapper.map(src -> src.getProduto(), MovimentacaoEstoque::setProduto);
		});
		return mapper.map(input, MovimentacaoEstoque.class);
	}
	
	public static MovimentacaoEstoqueOutput toOutput(MovimentacaoEstoque entity) {
		mapper.typeMap(MovimentacaoEstoque.class, MovimentacaoEstoqueOutput.class).addMappings(mapper -> {
			mapper.map(src -> src.getId(), MovimentacaoEstoqueOutput::setKey);
			mapper.map(src -> src.getProduto(), MovimentacaoEstoqueOutput::setProduto);
		});
		var output = mapper.map(entity, MovimentacaoEstoqueOutput.class);
		output.setProduto(ProdutoConverter.toOutput(entity.getProduto()));
		return addLinksHATEOAS(output);
	}
	
	public static List<MovimentacaoEstoque> toEntityList(List<MovimentacaoEstoqueInput> inputs) {
		List<MovimentacaoEstoque> entities = new ArrayList<>();
		inputs.forEach(input -> {
			entities.add(toEntity(input));
		}); 
		return entities;
	}
	
	public static List<MovimentacaoEstoqueOutput> toOutputList(List<MovimentacaoEstoque> entities) {
		List<MovimentacaoEstoqueOutput> outputs = new ArrayList<>();
		entities.forEach(entity -> {
			outputs.add(toOutput(entity));
		}); 
		return outputs;
	}
	
	private static MovimentacaoEstoqueOutput addLinksHATEOAS(MovimentacaoEstoqueOutput output) {
		output.add(linkTo(methodOn(MovimentacaoEstoqueController.class).findById(output.getKey())).withSelfRel());
		output.add(linkTo(methodOn(MovimentacaoEstoqueController.class).getEstoqueAtual()).withRel("atual-estoque"));
		return output;
	}
}