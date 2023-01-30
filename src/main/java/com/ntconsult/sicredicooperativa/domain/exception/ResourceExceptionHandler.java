package com.ntconsult.sicredicooperativa.domain.exception;

import java.time.Instant;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * 
 * @author vanderson
 * 
 * Classe responsável por capturar as exceções lançadas e apresentar o erro de forma padronizada como retorno da requisição de recursos
 *
 * Cada método anotado com @ExceptionHandler representa um tipo de exceção capturada
 */
@ControllerAdvice
public class ResourceExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){ 
		String message = this.messageSource.getMessage("message.exception.invalide.params.resume", null, Locale.getDefault());
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(VotingSessionAlreadyAssociatedException.class)
	public ResponseEntity<StandardError> existingAgenda(VotingSessionAlreadyAssociatedException exception, HttpServletRequest request){ 
		String message = this.messageSource.getMessage("message.exception.session.already.associated.to.agenda.resume", null, Locale.getDefault());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(ExistingAgendaException.class)
	public ResponseEntity<StandardError> pautaExistente(ExistingAgendaException exception, HttpServletRequest request){ 
		String message = this.messageSource.getMessage("message.exception.agenda.alread.registered.resume", null, Locale.getDefault());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(ClosedVotingSessionException.class)
	public ResponseEntity<StandardError> pautaExistente(ClosedVotingSessionException exception, HttpServletRequest request){ 
		String message = this.messageSource.getMessage("message.exception.session.closed.to.voting.resume", null, Locale.getDefault());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(VotingSessionNotRegisteredException.class)
	public ResponseEntity<StandardError> pautaExistente(VotingSessionNotRegisteredException exception, HttpServletRequest request){ 
		String message = this.messageSource.getMessage("message.exception.session.not.registered.resume", null, Locale.getDefault());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(ComputedVoteException.class)
	public ResponseEntity<StandardError> pautaExistente(ComputedVoteException exception, HttpServletRequest request){ 
		String message = this.messageSource.getMessage("message.exception.vote.already.computed.resume", null, Locale.getDefault());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(AgendaNotRegisteredException.class)
	public ResponseEntity<StandardError> pautaExistente(AgendaNotRegisteredException exception, HttpServletRequest request){ 
		String message = this.messageSource.getMessage("message.exception.session.without.registered.agenda.resume", null, Locale.getDefault());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(VotingSessionAlreadyClosedException.class)
	public ResponseEntity<StandardError> pautaExistente(VotingSessionAlreadyClosedException exception, HttpServletRequest request){ 
		String message = this.messageSource.getMessage("message.exception.session.already.closed.resume", null, Locale.getDefault());
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(VotesAlreadyCountedException.class)
	public ResponseEntity<StandardError> pautaExistente(VotesAlreadyCountedException exception, HttpServletRequest request){ 
		String message = new String(this.messageSource.getMessage("message.exception.session.votes.alread.counted.resume", null, Locale.getDefault()));
		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		return ResponseEntity.status(httpStatus).body(this.standardErrorFactory(httpStatus, message, exception, request));
	}
	
	@ExceptionHandler(OpenVotingSessionException.class)
	public ResponseEntity<StandardError> pautaExistente(OpenVotingSessionException exception, HttpServletRequest request){ 
		String message = this.messageSource.getMessage("message.exception.session.open.resume", null, Locale.getDefault());;
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
