package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.api.dto.form.VotingSessionForm;
import com.ntconsult.sicredicooperativa.domain.entity.Agenda;
import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.exception.AgendaNotRegisteredException;
import com.ntconsult.sicredicooperativa.domain.exception.VotingSessionAlreadyAssociatedException;
import com.ntconsult.sicredicooperativa.domain.repository.AgendaRepository;
import com.ntconsult.sicredicooperativa.domain.repository.VotingSessionRepository;

/**
 * 
 * @author vanderson 
 * 
 * Classe reponsável por realizar as validações necessárias antes de registrar uma nova sessão de votação
 *
 */
@Component
public class VotingSessionValidator implements EntityValidator<VotingSessionForm> {
	
	@Autowired
	private AgendaRepository agendaRepository;
	
	@Autowired
	private VotingSessionRepository votingSessionRepository;
	
	@Override
	public void validate(VotingSessionForm votingSessionForm) {
		this.validateAssociatedSessionToAgenda(votingSessionForm.getAgendaCode());
	}

	/**
	 * 
	 * @param associatedAgendaCode Código da pauta associada a sessão a ser aberta
	 * 
	 * Caso já exista uma sessão (aberta ou fechada) associada à pauta informada é lançada a exceção {@link VotingSessionAlreadyAssociatedException}
	 * 
	 * Caso a pauta informada não tenha sido cadastrada antes, é lançada a exceção {@link AgendaNotRegisteredException}
	 */
	private void validateAssociatedSessionToAgenda(String associatedAgendaCode) {
		Optional<Agenda> agenda = this.agendaRepository.findByCode(associatedAgendaCode);
		
		if(agenda.isPresent()) {
			Optional<VotingSession> session = this.votingSessionRepository.findByAgenda(agenda.get());
			if (session.isPresent()) {
				throw new VotingSessionAlreadyAssociatedException(String.format("Já existe uma sessão de votação associada à pauta de código %s", associatedAgendaCode));
			}
		}else {
			throw new AgendaNotRegisteredException(String.format("A pauta de código %s precisa ser cadastrada previamente", associatedAgendaCode));
		}
	}
}
