package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.api.dto.form.AgendaForm;
import com.ntconsult.sicredicooperativa.domain.entity.Agenda;
import com.ntconsult.sicredicooperativa.domain.exception.ExistingAgendaException;
import com.ntconsult.sicredicooperativa.domain.repository.AgendaRepository;

@Component
public class AgendaValidator implements EntityValidator<AgendaForm>{
	
	@Autowired
	private AgendaRepository agendaRepository;

	@Override
    public void validate(AgendaForm agenda) {
        validateExistingAgenda(agenda);
    }

    private void validateExistingAgenda(AgendaForm agendaForm){
		 Optional<Agenda> agenda = agendaRepository.findByCode(agendaForm.getCode());
		 
		 if(agenda.isPresent()) {
			 throw new ExistingAgendaException(String.format("Já existe uma pauta cadastrada com o código %s", agendaForm.getCode()));
		 }
    }
}
