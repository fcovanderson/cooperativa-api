package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Calendar;
import java.util.Locale;
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


/**
 * 
 * @author vanderson 
 * 
 * Classe reponsável por realizar as validações necessárias antes de seguir com computação de um voto
 *
 */
@Component
public class VoteValidator extends GenericValidator implements EntityValidator<VoteForm>{
	
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
	
	/**
	 * 
	 * @param votingSession Objeto que contém a sessão de votação que foi buscada anteriormente antes da chamada ao método validate.
	 * 
	 * Caso a sessão ainda não tenha sido cadastrada (igual a null), é lançada a execeção {@link VotingSessionNotRegisteredException}
	 * 
	 * Caso a sessão já tenha sido cadastrada mas esteja fechada para votação, é lançada a exceção {@link ClosedVotingSessionException}
	 */
	private void validateOpenSessionToVoting(Optional<VotingSession> votingSession) {
		if(votingSession.isPresent()) {
			long diffInMiliseconds = Calendar.getInstance().getTime().getTime() - votingSession.get().getOpenDate().getTime();
			long diffInMinutes = TimeUnit.MINUTES.convert(diffInMiliseconds, TimeUnit.MILLISECONDS);
			
			if(diffInMinutes > votingSession.get().getClosingDeadlineInMinutes()) {
				votingSession.get().setVotingSessionStatus(VotingSessionStatusEnum.CLOSED);
				this.votingSessionRepository.save(votingSession.get());
				throw new ClosedVotingSessionException(this.getMessageSource().getMessage("message.exception.session.closed.to.voting", null, Locale.getDefault()));
			}
		}else {
			throw new VotingSessionNotRegisteredException(this.getMessageSource().getMessage("message.exception.session.not.registered", null, Locale.getDefault()));
		}
	}
	
	/**
	 * 
	 * @param memberCode Código do associado que deve realizar o voto
	 * @param votingSession Objeto que contém a sessão de votação que foi buscada anteriormente antes da chamada ao método validate.
	 * 
	 * Caso o associado já tenha realizado voto na sessão, é lançada a exceção {@link ComputedVoteException}
	 */
	private void validateComputedVote(String memberCode, Optional<VotingSession> votingSession) {
		Optional<Vote> vote = this.voteRepository.findByMemberCodeAndVotingSession(memberCode, votingSession.get());
		if(vote.isPresent()) {
			throw new ComputedVoteException(this.getMessageSource().getMessage("message.exception.vote.already.computed", new String[] {memberCode, votingSession.get().getSessionCode()}, Locale.getDefault()));
		}
	}
}
