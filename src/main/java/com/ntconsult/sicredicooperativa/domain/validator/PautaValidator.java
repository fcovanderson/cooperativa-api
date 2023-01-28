package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.api.dto.form.PautaForm;
import com.ntconsult.sicredicooperativa.domain.entity.Pauta;
import com.ntconsult.sicredicooperativa.domain.exception.PautaExistenteException;
import com.ntconsult.sicredicooperativa.domain.repository.PautaRepository;

@Component
public class PautaValidator implements EntityValidator<PautaForm>{
	
	@Autowired
	private PautaRepository pautaRepository;

	@Override
    public void validate(PautaForm pauta) {
        validarPautaExistente(pauta);
    }

    private void validarPautaExistente(PautaForm pautaForm){
		 Optional<Pauta> pauta = pautaRepository.findByCodigo(pautaForm.getCodigo());
		 
		 if(pauta.isPresent()) {
			 throw new PautaExistenteException(String.format("Já existe uma pauta cadastrada com o código %s", pautaForm.getCodigo()));
		 }
    }
}
