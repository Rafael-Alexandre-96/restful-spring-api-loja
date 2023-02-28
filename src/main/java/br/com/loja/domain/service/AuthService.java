package br.com.loja.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.loja.api.model.v1.security.AccountCredentialsVO;
import br.com.loja.api.model.v1.security.TokenVO;
import br.com.loja.domain.repository.UserRepository;
import br.com.loja.security.jwt.JwtTokenProvider;

@Service
public class AuthService {

	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repository;
	
	public ResponseEntity<TokenVO> signin(AccountCredentialsVO data) {
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			var user = repository.findByUsername(username);
			var tokenResponse = new TokenVO();
			
			if (user != null) {
				tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " não encontrado!");
			}
			
			return ResponseEntity.ok(tokenResponse);
		} catch (Exception ex) {
			throw new BadCredentialsException("username/password inválidos!");
		}
	}
	
	public ResponseEntity<TokenVO> refreshToken(String username, String refreshToken) {
		var user = repository.findByUsername(username);
		var tokenResponse = new TokenVO();
		
		if (user != null) {
			tokenResponse = tokenProvider.refreshToken(refreshToken);
		} else {
			throw new UsernameNotFoundException("Username " + username + " não encontrado!");
		}
		
		return ResponseEntity.ok(tokenResponse);
	}
}