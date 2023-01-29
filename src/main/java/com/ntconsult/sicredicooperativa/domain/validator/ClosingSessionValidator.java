package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.enums.VotingSessionStatusEnum;
import com.ntconsult.sicredicooperativa.domain.exception.VotingSessionAlreadyClosedException;
import com.ntconsult.sicredicooperativa.domain.exception.VotingSessionNotRegisteredException;

@Component
public class ClosingSessionValidator implements EntityValidator<Optional<VotingSession>>{
	
	@Override
	public void validate(Optional<VotingSession> votingSession) {
		this.validateExistingSession(votingSession);
		this.validateClosedSession(votingSession);
	}
	
	public void validateExistingSession(Optional<VotingSession> votingSession) {
		if(votingSession.isEmpty()) {
			throw new VotingSessionNotRegisteredException("A sessão de votação informada ainda não foi aberta");
		}
	}
	
	private void validateClosedSession(Optional<VotingSession> votingSession) {
		if(votingSession.get().getVotingSessionStatus().equals(VotingSessionStatusEnum.CLOSED)) {
			throw new VotingSessionAlreadyClosedException(String.format("A sessão de votação %s já foi encerrada anteriormente", votingSession.get().getSessionCode()));
		}
	}
}
