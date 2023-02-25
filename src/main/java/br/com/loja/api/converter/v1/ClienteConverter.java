package br.com.loja.api.converter.v1;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.loja.api.model.v1.input.ClienteInput;
import br.com.loja.api.model.v1.output.ClienteOutput;
import br.com.loja.domain.model.Cliente;

public class ClienteConverter {
	static ModelMapper mapper = new ModelMapper();
	
	public static Cliente toEntity(ClienteInput input) {
		mapper.typeMap(ClienteInput.class, Cliente.class).addMappings(mapper -> {
			mapper.map(src -> src.getEndereco(), Cliente::setEndereco);
		});
		return mapper.map(input, Cliente.class);
	}
	
	public static ClienteOutput toOutput(Cliente entity) {
		mapper.typeMap(Cliente.class, ClienteOutput.class).addMappings(mapper -> {
			mapper.map(src -> src.getId(), ClienteOutput::setKey);
			mapper.map(src -> src.getEndereco(), ClienteOutput::setEndereco);
		});
		var outputs = mapper.map(entity, ClienteOutput.class);
		return outputs;
	}
	
	public static List<Cliente> toEntityList(List<ClienteInput> inputs) {
		List<Cliente> entities = new ArrayList<>();
		inputs.forEach(input -> {
			entities.add(toEntity(input));
		}); 
		return entities;
	}
	
	public static List<ClienteOutput> toOutputList(List<Cliente> entities) {
		List<ClienteOutput> outputs = new ArrayList<>();
		entities.forEach(entity -> {
			outputs.add(toOutput(entity));
		}); 
		return outputs;
	}
}