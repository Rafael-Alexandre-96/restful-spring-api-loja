package br.com.loja.api.converter.v1;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

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
		var outputs = mapper.map(entity, ProdutoOutput.class);
		outputs.setCategorias(CategoriaConverter.toOutputList(entity.getCategorias()));
		return outputs;
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
}