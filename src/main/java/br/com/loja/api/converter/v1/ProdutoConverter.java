package br.com.loja.api.converter.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.loja.api.controller.MovimentacaoEstoqueController;
import br.com.loja.api.controller.ProdutoController;
import br.com.loja.api.model.v1.input.ProdutoInput;
import br.com.loja.api.model.v1.output.ProdutoOutput;
import br.com.loja.domain.model.Produto;

public class ProdutoConverter {
	static ModelMapper mapper = new ModelMapper();
	
	public static Produto toEntity(ProdutoInput input) {
		mapper.typeMap(ProdutoInput.class, Produto.class).addMappings(mapper -> {
			mapper.map(src -> src.getCategorias(), Produto::setCategorias);
		});
		return mapper.map(input, Produto.class);
	}
	
	public static ProdutoOutput toOutput(Produto entity) {
		mapper.typeMap(Produto.class, ProdutoOutput.class).addMappings(mapper -> {
			mapper.map(src -> src.getId(), ProdutoOutput::setKey);
			mapper.map(src -> src.getCategorias(), ProdutoOutput::setCategorias);
		});
		var output = mapper.map(entity, ProdutoOutput.class);
		output.setCategorias(CategoriaConverter.toOutputList(entity.getCategorias()));
		return addLinksHATEOAS(output);
	}
	
	public static List<Produto> toEntityList(List<ProdutoInput> inputs) {
		List<Produto> entities = new ArrayList<>();
		inputs.forEach(input -> {
			entities.add(toEntity(input));
		}); 
		return entities;
	}
	
	public static List<ProdutoOutput> toOutputList(List<Produto> entities) {
		List<ProdutoOutput> outputs = new ArrayList<>();
		entities.forEach(entity -> {
			outputs.add(toOutput(entity));
		}); 
		return outputs;
	}
	
	private static ProdutoOutput addLinksHATEOAS(ProdutoOutput output) {
		output.add(linkTo(methodOn(ProdutoController.class).findById(output.getKey())).withSelfRel());
		output.add(linkTo(methodOn(ProdutoController.class).activeById(output.getKey())).withRel("active-record"));
		output.add(linkTo(methodOn(MovimentacaoEstoqueController.class).getEstoqueAtualByIdProduto(output.getKey())).withRel("in-estoque"));
		return output;
	}
}