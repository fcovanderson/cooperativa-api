package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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

    private void validarPautaExistente(PautaForm pauta){
    	 Pauta pautaQuery = new Pauta();
    	 pautaQuery.setCodigo(pauta.getCodigo());
		 ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
		 Example<Pauta> example = Example.of(pautaQuery, caseInsensitiveExampleMatcher);
		 Optional<Pauta> atual = pautaRepository.findOne(example);
		 
		 if(atual.isPresent()) {
			 throw new PautaExistenteException(String.format("Já existe uma pauta cadastrada com o código %s", pauta.getCodigo()));
		 }
    }
}
