package com.ntconsult.sicredicooperativa.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ntconsult.sicredicooperativa.api.dto.form.VotoForm;
import com.ntconsult.sicredicooperativa.domain.entity.Voto;
import com.ntconsult.sicredicooperativa.domain.service.VotoService;

import lombok.Getter;

@Getter
@RestController
@RequestMapping("api/v1/voto")
public class VotoController {
	
	@Autowired
	private VotoService votoService;
	
	@PostMapping
	public ResponseEntity<Voto> computarVoto(@Validated @RequestBody VotoForm votoForm) {
		Voto voto = this.votoService.save(votoForm);
		return ResponseEntity.ok().body(voto);
	}
}
