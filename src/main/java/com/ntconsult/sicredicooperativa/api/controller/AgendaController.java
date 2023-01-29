package com.ntconsult.sicredicooperativa.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntconsult.sicredicooperativa.api.dto.form.AgendaForm;
import com.ntconsult.sicredicooperativa.domain.entity.Agenda;
import com.ntconsult.sicredicooperativa.domain.service.AgendaService;

import lombok.Getter;

/**
 * 
 * @author vanderson
 * 
 * Classe responsável pelo gerenciamento de pautas
 *
 */

@Getter
@RestController
@RequestMapping("api/v1/agendas")
public class AgendaController {
	
	@Autowired
	private AgendaService agendaService;
	
	/**
	 * 
	 * @param agendaForm Informações referentes ao código e descrição da pauta
	 * @return Informações da pauta criada
	 */
	@PostMapping
	public ResponseEntity<Agenda> saveAgenda(@Validated @RequestBody AgendaForm agendaForm) {
		Agenda agenda = this.agendaService.save(agendaForm);
		return ResponseEntity.ok().body(agenda);
	}
}
