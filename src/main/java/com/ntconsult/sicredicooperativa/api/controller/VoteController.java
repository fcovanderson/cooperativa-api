package com.ntconsult.sicredicooperativa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntconsult.sicredicooperativa.api.dto.form.VoteForm;
import com.ntconsult.sicredicooperativa.domain.entity.Vote;
import com.ntconsult.sicredicooperativa.domain.service.VoteService;

import lombok.Getter;

/**
 * 
 * @author vanderson
 * 
 * Classe responsável pelo gerenciamento de votos
 *
 */

@Getter
@RestController
@RequestMapping("api/v1/vote")
public class VoteController {
	
	@Autowired
	private VoteService voteService;
	
	/**
	 * 
	 * @param voteForm Informações referentes ao código da sessão, código do associado e tipo do voto (SIM ou NAO)
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Vote> computeVote(@Validated @RequestBody VoteForm voteForm) {
		Vote vote = this.voteService.save(voteForm);
		return ResponseEntity.ok().body(vote);
	}
}
