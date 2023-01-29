package com.ntconsult.sicredicooperativa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntconsult.sicredicooperativa.api.dto.form.VoteForm;
import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.entity.Vote;
import com.ntconsult.sicredicooperativa.domain.repository.VotingSessionRepository;
import com.ntconsult.sicredicooperativa.domain.repository.VoteRepository;
import com.ntconsult.sicredicooperativa.domain.validator.VoteValidator;

@Service
public class VoteService implements GenericService<VoteForm, Vote>{
	
	@Autowired 
	private VoteRepository voteRepository;
	
	@Autowired 
	private VotingSessionRepository VotingSessionRepository;
	
	@Autowired 
	private VoteValidator voteValidator ;
	
	@Override
	public Vote save(VoteForm voteForm) {
		this.voteValidator.validate(voteForm);
		VotingSession votingSession = VotingSessionRepository.findBySessionCode(voteForm.getVotingSessionCode()).get();
		Vote newVote = new Vote(voteForm.getMemberCode(), voteForm.getVoteType(), votingSession);
		return voteRepository.save(newVote);
	}
}
