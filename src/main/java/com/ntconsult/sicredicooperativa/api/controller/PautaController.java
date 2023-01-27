package com.ntconsult.sicredicooperativa.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntconsult.sicredicooperativa.api.dto.form.PautaForm;
import com.ntconsult.sicredicooperativa.domain.service.PautaService;

import lombok.Getter;

@Getter
@RestController
@RequestMapping("api/v1/pautas")
public class PautaController {
	
	@Autowired
	private PautaService pautaService;

	@PostMapping
	public ResponseEntity<Object> salvarFalha(@Validated @RequestBody PautaForm pauta) {
		this.pautaService.save(pauta);
		return ResponseEntity.ok().build();
	}
}
