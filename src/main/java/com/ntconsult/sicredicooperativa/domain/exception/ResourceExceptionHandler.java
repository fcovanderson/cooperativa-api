package com.ntconsult.sicredicooperativa.domain.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 
 * @author vanderson
 * 
 * Classe responsável por capturar as exceções lançadas e apresentar o erro de forma padronizada como retorno da requisição de recursos
 *
 */
@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){ 
		String message = new String("Campo(s) obrigatório(s) não informado(s) ou inválido(s)");
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(VotingSessionAlreadyAssociatedException.class)
	public ResponseEntity<StandardError> existingAgenda(VotingSessionAlreadyAssociatedException exception, HttpServletRequest request){ 
		String message = new String("Entidade já cadastrada na base");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(ExistingAgendaException.class)
	public ResponseEntity<StandardError> pautaExistente(ExistingAgendaException exception, HttpServletRequest request){ 
		String message = new String("Entidade já cadastrada na base");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(ClosedVotingSessionException.class)
	public ResponseEntity<StandardError> pautaExistente(ClosedVotingSessionException exception, HttpServletRequest request){ 
		String message = new String("A sessão está fechada para votação");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(VotingSessionNotRegisteredException.class)
	public ResponseEntity<StandardError> pautaExistente(VotingSessionNotRegisteredException exception, HttpServletRequest request){ 
		String message = new String("A sessão de votação precisa ser aberta previamente");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(ComputedVoteException.class)
	public ResponseEntity<StandardError> pautaExistente(ComputedVoteException exception, HttpServletRequest request){ 
		String message = new String("Voto já computado anteriomente");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(AgendaNotRegisteredException.class)
	public ResponseEntity<StandardError> pautaExistente(AgendaNotRegisteredException exception, HttpServletRequest request){ 
		String message = new String("Pauta ainda não cadastrada");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(VotingSessionAlreadyClosedException.class)
	public ResponseEntity<StandardError> pautaExistente(VotingSessionAlreadyClosedException exception, HttpServletRequest request){ 
		String message = new String("A sessão de votação já se encontra encerrada");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(VotesAlreadyCountedException.class)
	public ResponseEntity<StandardError> pautaExistente(VotesAlreadyCountedException exception, HttpServletRequest request){ 
		String message = new String("Votos já contabilizados anteriormente");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(OpenVotingSessionException.class)
	public ResponseEntity<StandardError> pautaExistente(OpenVotingSessionException exception, HttpServletRequest request){ 
		String message = new String("A sessão de votação precisa ser encerrada previamente");
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
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
