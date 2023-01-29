package com.ntconsult.sicredicooperativa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntconsult.sicredicooperativa.api.dto.form.VotingSessionForm;
import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.service.VotingSessionService;

import lombok.Getter;

/**
 * 
 * @author vanderson
 * 
 * Classe responsável pelo gerenciamento de sessões de votação 
 *
 */


@Getter
@RestController
@RequestMapping("api/v1/votingSession")
public class VotingSessionController {
	
	@Autowired
	private VotingSessionService votingSessionService;
	
	/**
	 * 
	 * @param votingSessionForm Informações referentes ao código da sessão, prazo de término em minutos e códig da pauta associada
	 * @return Sessão de votação aberta em caso de sucesso
	 */
	@PostMapping
	public ResponseEntity<VotingSession> openVotingSession(@Validated @RequestBody VotingSessionForm votingSessionForm) {
		VotingSession votingSession = this.votingSessionService.save(votingSessionForm);
		return ResponseEntity.ok().body(votingSession);
	}
	
	/**
	 * 
	 * @param sessionCode Código da sessão a ser fechada
	 * @return Sessão de votação atualizada
	 */
	@PostMapping("/close/{sessionCode}")
	public ResponseEntity<VotingSession> closeSession(@PathVariable String sessionCode) {
		return ResponseEntity.ok().body(this.votingSessionService.closeSession(sessionCode));
	}
	
	
	/**
	 * 
	 * @param sessionCode Código da sessão que deverá ter os votos contabilizados
	 * @return Sessão de votação atualizada com as informações do resultado 
	 */
	@PostMapping("/countVotes/{sessionCode}")
	public ResponseEntity<VotingSession> countSessionVotes(@PathVariable String sessionCode) {
		return ResponseEntity.ok().body(this.votingSessionService.countVotes(sessionCode));
	}
}
