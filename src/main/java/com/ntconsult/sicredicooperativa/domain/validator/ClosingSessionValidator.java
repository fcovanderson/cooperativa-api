package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Locale;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.enums.VotingSessionStatusEnum;
import com.ntconsult.sicredicooperativa.domain.exception.VotingSessionAlreadyClosedException;
import com.ntconsult.sicredicooperativa.domain.exception.VotingSessionNotRegisteredException;

/**
 * 
 * @author vanderson 
 * 
 * Classe reponsável por realizar as validações necessárias antes de realizar o fechamento de uma sessão
 *
 */
@Component
public class ClosingSessionValidator extends GenericValidator implements EntityValidator<Optional<VotingSession>>{
	
	@Override
	public void validate(Optional<VotingSession> votingSession) {
		this.validateExistingSession(votingSession);
		this.validateClosedSession(votingSession);
	}
	
	/**
	 * 
	 * @param votingSession Objeto que contém a sessão de votação que foi buscada anteriormente antes da chamada ao método validate.
	 * 
	 * Caso a sessão seja null, indicando que não foi retornada nenhuma sessão na busca, será lançada a exceção {@link VotingSessionNotRegisteredException}
	 */
	public void validateExistingSession(Optional<VotingSession> votingSession) {
		if(votingSession.isEmpty()) {
			throw new VotingSessionNotRegisteredException(this.getMessageSource().getMessage("message.exception.session.not.registered", null, Locale.getDefault()));
		}
	}
	
	/**
	 * @param votingSession Objeto que contém a sessão de votação que foi buscada anteriormente antes da chamada ao método validate.
	 * 
	 * Caso a sessão já tenha sido encerrada antes, será lançada a exceção {@link VotingSessionAlreadyClosedException}
	 */
	private void validateClosedSession(Optional<VotingSession> votingSession) {
		if(votingSession.get().getVotingSessionStatus().equals(VotingSessionStatusEnum.CLOSED)) {
			throw new VotingSessionAlreadyClosedException(this.getMessageSource().getMessage("message.exception.session.already.closed", new String[] {votingSession.get().getSessionCode()}, Locale.getDefault()));
		}
	}
}
