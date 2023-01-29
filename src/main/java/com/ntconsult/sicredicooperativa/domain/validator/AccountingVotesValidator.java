package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.enums.VotingSessionStatusEnum;
import com.ntconsult.sicredicooperativa.domain.exception.OpenVotingSessionException;
import com.ntconsult.sicredicooperativa.domain.exception.VotesAlreadyCountedException;
import com.ntconsult.sicredicooperativa.domain.exception.VotingSessionNotRegisteredException;

/**
 * 
 * @author vanderson 
 * 
 * Classe reponsável por realizar as validações necessárias antes de seguir com a contabilização de votos para uma sessão específica
 *
 */
@Component
public class AccountingVotesValidator extends GenericValidator implements EntityValidator<Optional<VotingSession>>{
	
	@Autowired
	private ClosingSessionValidator closingSessionValidator;
	
	/**
	 * @param sessaoDeVotacao Objeto que contém a sessão de votação que foi buscada anteriormente antes da chamada ao método validate.
	 * 
	 * Se a sessão for null, indicando que não foi retornada nenhuma sessão na busca, será lançada a exceção {@link VotingSessionNotRegisteredException} durante a chamada do método validateExistingSession
	 */
	@Override
	public void validate(Optional<VotingSession> votingSession) {
		this.closingSessionValidator.validateExistingSession(votingSession);
		this.validadeCountedVotes(votingSession);
		this.validateUnclosedSession(votingSession);
	}
	
	/**
	 * 
	 * @param sessaoDeVotacao Objeto que contém a sessão de votação que foi buscada anteriormente antes da chamada ao método validate
	 * 
	 * Caso os votos da sessão já tenham sido contabilizados é lançada a exceção {@link VotesAlreadyCountedException}
	 */
	private void validadeCountedVotes(Optional<VotingSession> sessaoDeVotacao){
		if(Objects.nonNull(sessaoDeVotacao.get().getAgenda().getStatus())) {
			throw new VotesAlreadyCountedException(this.getMessageSource().getMessage("message.exception.session.votes.alread.counted", new Object[] {sessaoDeVotacao.get().getSessionCode()}, Locale.getDefault()));
		}
	}
	
	/**
	 * 
	 * @param sessaoDeVotacao Objeto que contém a sessão de votação que foi buscada anteriormente antes da chamada ao método validate
	 * 
	 * Caso a sessão de votação ainda esteja em aberto é lançada a execeção {@link OpenVotingSessionException}
	 */
	private void validateUnclosedSession(Optional<VotingSession> sessaoDeVotacao) {
		if(sessaoDeVotacao.get().getVotingSessionStatus().equals(VotingSessionStatusEnum.OPEN)) {
			throw new OpenVotingSessionException(this.getMessageSource().getMessage("message.exception.session.open", new Object[] {sessaoDeVotacao.get().getSessionCode()}, Locale.getDefault()));
		}
	}
}
