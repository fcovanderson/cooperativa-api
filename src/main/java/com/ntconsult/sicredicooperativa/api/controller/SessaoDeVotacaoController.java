package com.ntconsult.sicredicooperativa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntconsult.sicredicooperativa.api.dto.form.SessaoDeVotacaoForm;
import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;
import com.ntconsult.sicredicooperativa.domain.service.SessaoDeVotacaoService;

import lombok.Getter;

@Getter
@RestController
@RequestMapping("api/v1/sessaoVotacao")
public class SessaoDeVotacaoController {
	
	@Autowired
	private SessaoDeVotacaoService sessaoDeVotacaoService;
	
	@PostMapping
	public ResponseEntity<SessaoDeVotacao> abrirSessaoDeVotacao(@Validated @RequestBody SessaoDeVotacaoForm sessaoDeVotacaoForm) {
		SessaoDeVotacao sessaoDeVotacao = this.sessaoDeVotacaoService.save(sessaoDeVotacaoForm);
		return ResponseEntity.ok().body(sessaoDeVotacao);
	}
}
