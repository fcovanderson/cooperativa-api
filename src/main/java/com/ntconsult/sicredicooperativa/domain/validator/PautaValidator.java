package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.domain.entity.Pauta;
import com.ntconsult.sicredicooperativa.domain.exception.PautaExistenteException;
import com.ntconsult.sicredicooperativa.domain.repository.PautaRepository;

@Component
public class PautaValidator implements EntityValidator<Pauta>{
	
	@Autowired
	PautaRepository pautaRepository;

	@Override
    public void validate(Pauta pauta) {
        validarPautaExistente(pauta);
    }

    private void validarPautaExistente(Pauta pauta){
    	 Pauta pautaQuery = new Pauta();
    	 pautaQuery.setCodigo(pautaQuery.getCodigo());
		 ExampleMatcher caseInsensitiveExampleMatcher = ExampleMatcher.matchingAll().withIgnoreCase();
		 Example<Pauta> example = Example.of(pautaQuery, caseInsensitiveExampleMatcher);
		 Optional<Pauta> atual = pautaRepository.findOne(example);
		 
		 if(atual.isPresent()) {
			 throw new PautaExistenteException(String.format("Já existe uma pauta cadastrada com o código %s", pauta.getCodigo() ));
		 }
    }
}
