package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.enums.VotingSessionStatusEnum;
import com.ntconsult.sicredicooperativa.domain.exception.OpenVotingSessionException;
import com.ntconsult.sicredicooperativa.domain.exception.VotesAlreadyCountedException;

@Component
public class AccountingVotesValidator implements EntityValidator<Optional<VotingSession>>{
	
	@Autowired
	private ClosingSessionValidator closingSessionValidator;
	
	@Override
	public void validate(Optional<VotingSession> votingSession) {
		this.closingSessionValidator.validateExistingSession(votingSession);
		this.validadeCountedVotes(votingSession);
		this.validateUnclosedSession(votingSession);
	}
	
	private void validadeCountedVotes(Optional<VotingSession> sessaoDeVotacao){
		if(Objects.nonNull(sessaoDeVotacao.get().getAgenda().getStatus())) {
			throw new VotesAlreadyCountedException(String.format("Os votos para a sessão de votação %s já foram contabilizados", sessaoDeVotacao.get().getSessionCode()));
		}
	}
	
	private void validateUnclosedSession(Optional<VotingSession> sessaoDeVotacao) {
		if(sessaoDeVotacao.get().getVotingSessionStatus().equals(VotingSessionStatusEnum.OPEN)) {
			throw new OpenVotingSessionException(String.format("A sessão de votação %s ainda está em aberto", sessaoDeVotacao.get().getSessionCode()));
		}
	}
}
