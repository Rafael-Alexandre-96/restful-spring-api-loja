package br.com.loja.api.exception.handler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.loja.api.exception.InvalidJwtAuthenticationException;
import br.com.loja.domain.exception.DomainException;
import br.com.loja.domain.exception.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
@RestController
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ExceptionResponse response = new ExceptionResponse(status, OffsetDateTime.now(), ex.getMessage(), request.getDescription(false));
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(InvalidJwtAuthenticationException.class)
	public ResponseEntity<Object> InvalidJwtAuthenticationHandler(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		ExceptionResponse response = new ExceptionResponse(status, OffsetDateTime.now(), ex.getMessage(), request.getDescription(false));
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> ConstraintViolationHandler(ConstraintViolationException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ExceptionResponse response = new ExceptionResponse(status, OffsetDateTime.now(), "Um ou mais campos estão inválidos. Faça o preenchimento correto", request.getDescription(false));
		List<ExceptionResponse.Campo> campos = new ArrayList<>();
		ex.getConstraintViolations().forEach(violation -> {
			ExceptionResponse.Campo campo = new ExceptionResponse.Campo();
			campo.setNome(violation.getPropertyPath().toString());
			campo.setMensagem(violation.getMessage());
			campos.add(campo);
		});
		response.setCampos(campos);
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> EntityNotFoundHandler(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		ExceptionResponse response = new ExceptionResponse(status, OffsetDateTime.now(), ex.getMessage(), request.getDescription(false));
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> DomainHandler(Exception ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ExceptionResponse response = new ExceptionResponse(status, OffsetDateTime.now(), ex.getMessage(), request.getDescription(false));
		return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
	}
}