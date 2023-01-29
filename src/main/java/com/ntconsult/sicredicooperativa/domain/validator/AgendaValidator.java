package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.api.dto.form.AgendaForm;
import com.ntconsult.sicredicooperativa.domain.entity.Agenda;
import com.ntconsult.sicredicooperativa.domain.exception.ExistingAgendaException;
import com.ntconsult.sicredicooperativa.domain.repository.AgendaRepository;

/**
 * 
 * @author vanderson 
 * 
 * Classe reponsável por realizar as validações necessárias antes de persistir uma nova pauta
 *
 */
@Component
public class AgendaValidator extends GenericValidator implements EntityValidator<AgendaForm>{
	
	@Autowired
	private AgendaRepository agendaRepository;

	@Override
    public void validate(AgendaForm agenda) {
        validateExistingAgenda(agenda);
    }
	
	/**
	 * 
	 * @param agendaForm Objeto que contém as informações do corpo da requisição para o serviço de salvar uma nova pauta
	 * 
	 * Caso já exista uma pauta cadastrada com o mesmo código informado, é lançada a execeção {@link ExistingAgendaException}
	 */
    private void validateExistingAgenda(AgendaForm agendaForm){
		 Optional<Agenda> agenda = agendaRepository.findByCode(agendaForm.getCode());
		 if(agenda.isPresent()) {
			 throw new ExistingAgendaException(this.getMessageSource().getMessage("message.exception.agenda.alread.registered", new String[] {agendaForm.getCode()}, Locale.getDefault()));
		 }
    }
}
