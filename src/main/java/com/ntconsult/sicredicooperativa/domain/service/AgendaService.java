package com.ntconsult.sicredicooperativa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntconsult.sicredicooperativa.api.dto.form.AgendaForm;
import com.ntconsult.sicredicooperativa.domain.entity.Agenda;
import com.ntconsult.sicredicooperativa.domain.repository.AgendaRepository;
import com.ntconsult.sicredicooperativa.domain.validator.AgendaValidator;


/**
 * 
 * @author vanderson
 * 
 * Classe gerenciada pelo framework spring e responsável a 'interface' de comunicação entre o controller e a repository a entidade Agenda
 *	
 * Realiza todas as regras de validação antes que as informações sejam persistidas na base. Caso alguma validação não seja satisfeita é lançada uma exceção e o processo de persistência não prossegue
 */
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
