package com.ntconsult.sicredicooperativa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntconsult.sicredicooperativa.api.dto.form.AgendaForm;
import com.ntconsult.sicredicooperativa.domain.entity.Agenda;
import com.ntconsult.sicredicooperativa.domain.repository.AgendaRepository;
import com.ntconsult.sicredicooperativa.domain.validator.AgendaValidator;

@Service
public class AgendaService implements GenericService<AgendaForm, Agenda> {
	
	@Autowired 
	private AgendaRepository agendaRepository;
	
	@Autowired 
	private AgendaValidator validator;
	
	@Override
	public Agenda save(AgendaForm agendaForm) {
		validator.validate(agendaForm);
		Agenda newAgenda = new Agenda(agendaForm.getDescription(), agendaForm.getCode());
		return agendaRepository.save(newAgenda);
	}
}
