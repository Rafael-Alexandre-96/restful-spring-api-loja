package br.com.loja.domain.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.loja.domain.exceptions.EntityNotFoundException;
import br.com.loja.domain.model.Cliente;
import br.com.loja.domain.repository.ClienteRepository;
import jakarta.validation.Valid;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente findById(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Cliente ID %s não encontrado!", id.toString())));
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	@Transactional
	public Cliente create(@Valid Cliente input) {
		input.setCreatedAt(OffsetDateTime.now());
		input = clienteRepository.save(input);
		return this.findById(input.getId());
	}
	
	@Transactional
	public Cliente update(Long id, @Valid Cliente input) {
		var finded = this.findById(id);
		
		finded.setNome(input.getNome());
		finded.setCelular(input.getCelular());
		finded.setEmail(input.getEmail());
		finded.setEndereco(input.getEndereco());
		finded.setUpdatedAt(OffsetDateTime.now());
		finded = clienteRepository.save(finded);
		
		return this.findById(id);
	}
	
	@Transactional
	public void deleteById(Long id) {
		var finded = this.findById(id);
		finded.setDeletedAt(OffsetDateTime.now());
		clienteRepository.save(finded);
	}
	
	@Transactional
	public void activeById(Long id) {
		var finded = this.findById(id);
		finded.setDeletedAt(null);
		clienteRepository.save(finded);
	}
}