package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.api.dto.form.VoteForm;
import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.entity.Vote;
import com.ntconsult.sicredicooperativa.domain.enums.VotingSessionStatusEnum;
import com.ntconsult.sicredicooperativa.domain.exception.ClosedVotingSessionException;
import com.ntconsult.sicredicooperativa.domain.exception.VotingSessionNotRegisteredException;
import com.ntconsult.sicredicooperativa.domain.exception.ComputedVoteException;
import com.ntconsult.sicredicooperativa.domain.repository.VotingSessionRepository;
import com.ntconsult.sicredicooperativa.domain.repository.VoteRepository;

@Component
public class VoteValidator implements EntityValidator<VoteForm>{
	
	@Autowired
	private VotingSessionRepository votingSessionRepository;
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Override
	public void validate(VoteForm voteForm) {
		Optional<VotingSession> votingSession = this.votingSessionRepository.findBySessionCode(voteForm.getVotingSessionCode());
		this.validateOpenSessionToVoting(votingSession);
		this.validateComputedVote(voteForm.getMemberCode(), votingSession);
	}
	
	private void validateOpenSessionToVoting(Optional<VotingSession> votingSession) {
		if(votingSession.isPresent()) {
			long diffInMiliseconds = Calendar.getInstance().getTime().getTime() - votingSession.get().getOpenDate().getTime();
			long diffInMinutes = TimeUnit.MINUTES.convert(diffInMiliseconds, TimeUnit.MILLISECONDS);
			
			if(diffInMinutes > votingSession.get().getClosingDeadlineInMinutes()) {
				votingSession.get().setVotingSessionStatus(VotingSessionStatusEnum.CLOSED);
				this.votingSessionRepository.save(votingSession.get());
				throw new ClosedVotingSessionException("A sessão se encontra encerrada para votação");
			}
		}else {
			throw new VotingSessionNotRegisteredException("A sessão de votação informada ainda não foi aberta");
		}
	}
	
	private void validateComputedVote(String memberCode, Optional<VotingSession> votingSession) {
		Optional<Vote> vote = this.voteRepository.findByAssociadoCodigoAndSessaoDeVotacao(memberCode, votingSession.get());
		if(vote.isPresent()) {
			throw new ComputedVoteException(String.format("O associado %s já realizou votação para a sessão %s", memberCode, votingSession.get().getSessionCode()));
		}
	}
}
