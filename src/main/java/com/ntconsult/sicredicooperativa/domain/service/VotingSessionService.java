package com.ntconsult.sicredicooperativa.domain.service;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntconsult.sicredicooperativa.api.dto.form.VotingSessionForm;
import com.ntconsult.sicredicooperativa.domain.entity.Agenda;
import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.enums.AgendaStatusEnum;
import com.ntconsult.sicredicooperativa.domain.enums.VotingSessionStatusEnum;
import com.ntconsult.sicredicooperativa.domain.enums.VoteEnum;
import com.ntconsult.sicredicooperativa.domain.repository.AgendaRepository;
import com.ntconsult.sicredicooperativa.domain.repository.VotingSessionRepository;
import com.ntconsult.sicredicooperativa.domain.validator.AccountingVotesValidator;
import com.ntconsult.sicredicooperativa.domain.validator.ClosingSessionValidator;
import com.ntconsult.sicredicooperativa.domain.validator.VotingSessionValidator;

/**
 * 
 * @author vanderson
 * 
 * Classe gerenciada pelo framework spring e responsável a 'interface' de comunicação entre o controller e a repository a entidade VotingSession
 *	
 * Realiza todas as regras de validação antes que as informações sejam persistidas na base. Caso alguma validação não seja satisfeita é lançada uma exceção e o processo de persistência não prossegue
 */
@Service
public class VotingSessionService implements GenericService<VotingSessionForm, VotingSession>{
	
	@Autowired 
	private VotingSessionRepository votingSessionRepository;
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired 
	private VotingSessionValidator validator;
	
	@Autowired 
	private ClosingSessionValidator closingSessionValidator;
	
	@Autowired 
	private AccountingVotesValidator accountingVotesValidator;
	
	@Override
	public VotingSession save(VotingSessionForm votingSessionForm) {
	    this.validator.validate(votingSessionForm);
	    Optional<Agenda> agenda = agendaRepository.findByCode(votingSessionForm.getAgendaCode());
		VotingSession votingSession = new VotingSession(agenda.get(), Calendar.getInstance().getTime(), votingSessionForm.getTimeOfClosingInMinutes(), votingSessionForm.getSessionCode());
		return this.votingSessionRepository.save(votingSession);
	}
	
	public VotingSession closeSession(String sessionCode) {
		Optional<VotingSession> votingSession = votingSessionRepository.findBySessionCode(sessionCode);
	    this.closingSessionValidator.validate(votingSession);
	    votingSession.get().setVotingSessionStatus(VotingSessionStatusEnum.CLOSED);
		return this.votingSessionRepository.save(votingSession.get());
	}
	
	public VotingSession countVotes(String sessionCode) {
		Optional<VotingSession> votingSession = votingSessionRepository.findBySessionCode(sessionCode);
	    this.accountingVotesValidator.validate(votingSession);
	    
	    long yesTotalVotes = votingSession.get().getVotes().stream().filter(vote -> vote.getVoteType().equals(VoteEnum.YES)).count();
	    long noTotalVotes = votingSession.get().getVotes().stream().filter(vote -> vote.getVoteType().equals(VoteEnum.NO)).count();
	    
	    votingSession.get().setYesTotalVotes(Integer.valueOf(String.valueOf(yesTotalVotes)));
	    votingSession.get().setNoTotalVotes(Integer.valueOf(String.valueOf(noTotalVotes)));
	    
	    votingSession.get().getAgenda().setStatus(yesTotalVotes > noTotalVotes ? AgendaStatusEnum.APPROVED : AgendaStatusEnum.REJECTED);
	    
		return this.votingSessionRepository.save(votingSession.get());
	}
}
