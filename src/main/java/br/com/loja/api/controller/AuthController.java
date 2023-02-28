package br.com.loja.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.loja.api.model.v1.security.AccountCredentialsVO;
import br.com.loja.domain.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticacao", description = "Endpoints para Autenticacao")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Autentica usuario e retorna o token")
	@PostMapping("/signin")
	public ResponseEntity signin(@RequestBody AccountCredentialsVO data) {
		if (checkIfParamsIsNotNull(data))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Requisição inválida!");
		
		var token = service.signin(data);
		
		if (token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Requisição inválida!");
		
		return token;
	}
	
	@SuppressWarnings("rawtypes")
	@Operation(summary = "Atualiza Token para usuario autenticado e retorna o token")
	@PutMapping("/refresh/{username}")
	public ResponseEntity refreshToken(@PathVariable("username") String username, @RequestHeader("Authorization") String refreshToken) {
		if (checkIfParamsIsNotNull(username, refreshToken))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Requisição inválida!");
		
		var token = service.refreshToken(username, refreshToken);
		
		if (token == null)
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Requisição inválida!");
		
		return token;
	}

	private boolean checkIfParamsIsNotNull(String username, String refreshToken) {
		return refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank();
	}

	private boolean checkIfParamsIsNotNull(AccountCredentialsVO data) {
		return data == null || data.getUsername() == null || data.getUsername().isBlank() || data.getPassword() == null || data.getPassword().isBlank();
	}
}