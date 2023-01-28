package com.ntconsult.sicredicooperativa.domain.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ntconsult.sicredicooperativa.domain.repository.SessaoDeVotacaoExistenteException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){ 
		String mensagem = new String("Campo(s) obrigatório(s) não informado(s) ou inválido(s)");
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, mensagem, exception, request));
	}
	
	@ExceptionHandler(SessaoDeVotacaoExistenteException.class)
	public ResponseEntity<StandardError> pautaExistente(SessaoDeVotacaoExistenteException exception, HttpServletRequest request){ 
		String mensagem = new String("Entidade já cadastrada na base");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, mensagem, exception, request));
	}
	
	@ExceptionHandler(PautaExistenteException.class)
	public ResponseEntity<StandardError> pautaExistente(PautaExistenteException exception, HttpServletRequest request){ 
		String mensagem = new String("Entidade já cadastrada na base");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, mensagem, exception, request));
	}
	
	private StandardError standardErrorFactory(HttpStatus httpStatus, String mensagem, Exception exception, HttpServletRequest request) {
		StandardError error = new StandardError();
		error.setTimestamp(Instant.now());
		error.setStatus(httpStatus.value());
		error.setError(mensagem);
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());
		return error;
	}
}