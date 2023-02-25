package br.com.loja.api.converter.v1;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.loja.api.model.v1.input.CategoriaInput;
import br.com.loja.api.model.v1.output.CategoriaOutput;
import br.com.loja.domain.model.Categoria;

public class CategoriaConverter {
	static ModelMapper mapper = new ModelMapper();
	
	public static Categoria toEntity(CategoriaInput input) {
		return mapper.map(input, Categoria.class);
	}
	
	public static CategoriaOutput toOutput(Categoria entity) {
		mapper.typeMap(Categoria.class, CategoriaOutput.class).addMappings(mapper -> {
			mapper.map(src -> src.getId(), CategoriaOutput::setKey);
		});
		return mapper.map(entity, CategoriaOutput.class);
	}
	
	public static List<Categoria> toEntityList(List<CategoriaInput> inputs) {
		List<Categoria> entities = new ArrayList<>();
		inputs.forEach(input -> {
			entities.add(toEntity(input));
		}); 
		return entities;
	}
	
	public static List<CategoriaOutput> toOutputList(List<Categoria> entities) {
		List<CategoriaOutput> outputs = new ArrayList<>();
		entities.forEach(entity -> {
			outputs.add(toOutput(entity));
		}); 
		return outputs;
	}
}