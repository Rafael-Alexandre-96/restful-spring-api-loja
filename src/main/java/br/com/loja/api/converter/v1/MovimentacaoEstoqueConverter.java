package br.com.loja.api.converter.v1;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

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
		var outputs = mapper.map(entity, MovimentacaoEstoqueOutput.class);
		outputs.setProduto(ProdutoConverter.toOutput(entity.getProduto()));
		return outputs;
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
}