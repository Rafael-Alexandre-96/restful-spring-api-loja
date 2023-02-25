package br.com.loja.api.converter.v1;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.loja.api.model.v1.input.ItemVendaInput;
import br.com.loja.api.model.v1.output.ItemVendaOutput;
import br.com.loja.domain.model.ItemVenda;

public class ItemVendaConverter {
	static ModelMapper mapper = new ModelMapper();
	
	public static ItemVenda toEntity(ItemVendaInput input) {
		mapper.typeMap(ItemVendaInput.class, ItemVenda.class).addMappings(mapper -> {
			mapper.map(src -> src.getProduto(), ItemVenda::setProduto);
		});
		return mapper.map(input, ItemVenda.class);
	}
	
	public static ItemVendaOutput toOutput(ItemVenda entity) {
		mapper.typeMap(ItemVenda.class, ItemVendaOutput.class).addMappings(mapper -> {
			mapper.map(src -> src.getProduto(), ItemVendaOutput::setProduto);
		});
		var outputs = mapper.map(entity, ItemVendaOutput.class);
		outputs.setProduto(ProdutoConverter.toOutput(entity.getProduto()));
		return outputs;
	}
	
	public static List<ItemVenda> toEntityList(List<ItemVendaInput> inputs) {
		List<ItemVenda> entities = new ArrayList<>();
		inputs.forEach(input -> {
			entities.add(toEntity(input));
		}); 
		return entities;
	}
	
	public static List<ItemVendaOutput> toOutputList(List<ItemVenda> entities) {
		List<ItemVendaOutput> outputs = new ArrayList<>();
		entities.forEach(entity -> {
			outputs.add(toOutput(entity));
		}); 
		return outputs;
	}
}