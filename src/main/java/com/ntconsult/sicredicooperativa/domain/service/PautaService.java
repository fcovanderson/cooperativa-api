package com.ntconsult.sicredicooperativa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntconsult.sicredicooperativa.api.dto.form.PautaForm;
import com.ntconsult.sicredicooperativa.domain.entity.Pauta;
import com.ntconsult.sicredicooperativa.domain.repository.PautaRepository;
import com.ntconsult.sicredicooperativa.domain.validator.PautaValidator;

@Service
public class PautaService implements GenericService {
	
	@Autowired 
	private PautaRepository pautaRepository;
	
	@Autowired 
	private PautaValidator validator;
	
	@Override
	public Pauta save(PautaForm pauta) {
		Pauta novaPauta = new Pauta(pauta.getDescricao(), pauta.getCodigo());
		validator.validate(novaPauta);
		return pautaRepository.save(novaPauta);
	}
}
