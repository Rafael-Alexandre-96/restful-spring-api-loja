package br.com.loja.api.converter.v1;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.loja.api.controller.VendaController;
import br.com.loja.api.model.v1.input.VendaInput;
import br.com.loja.api.model.v1.output.VendaOutput;
import br.com.loja.domain.model.Venda;

public class VendaConverter {
	static ModelMapper mapper = new ModelMapper();
	
	public static Venda toEntity(VendaInput input) {
		mapper.typeMap(VendaInput.class, Venda.class).addMappings(mapper -> {
			mapper.map(src -> src.getCliente(), Venda::setCliente);
		});
		var entity = mapper.map(input, Venda.class);
		entity.setItens(ItemVendaConverter.toEntityList(input.getItens()));
		return entity;
	}
	
	public static VendaOutput toOutput(Venda entity) {
		mapper.typeMap(Venda.class, VendaOutput.class).addMappings(mapper -> {
			mapper.map(src -> src.getId(), VendaOutput::setKey);
			mapper.map(src -> src.getCliente(), VendaOutput::setCliente);
		});
		var output = mapper.map(entity, VendaOutput.class);
		output.setCliente(ClienteConverter.toOutput(entity.getCliente()));
		output.setItens(ItemVendaConverter.toOutputList(entity.getItens()));
		return addLinksHATEOAS(output);
	}
	
	public static List<Venda> toEntityList(List<VendaInput> inputs) {
		List<Venda> entities = new ArrayList<>();
		inputs.forEach(input -> {
			entities.add(toEntity(input));
		}); 
		return entities;
	}
	
	public static List<VendaOutput> toOutputList(List<Venda> entities) {
		List<VendaOutput> outputs = new ArrayList<>();
		entities.forEach(entity -> {
			outputs.add(toOutput(entity));
		}); 
		return outputs;
	}
	
	private static VendaOutput addLinksHATEOAS(VendaOutput output) {
		output.add(linkTo(methodOn(VendaController.class).findById(output.getKey())).withSelfRel());
		output.add(linkTo(methodOn(VendaController.class).finalizeVenda(output.getKey())).withRel("finalize-venda"));
		output.add(linkTo(methodOn(VendaController.class).cancelVenda(output.getKey())).withRel("cancel-venda"));
		return output;
	}
}