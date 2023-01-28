package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.api.dto.form.SessaoDeVotacaoForm;
import com.ntconsult.sicredicooperativa.domain.entity.Pauta;
import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;
import com.ntconsult.sicredicooperativa.domain.repository.PautaRepository;
import com.ntconsult.sicredicooperativa.domain.repository.SessaoDeVotacaoExistenteException;
import com.ntconsult.sicredicooperativa.domain.repository.SessaoDeVotacaoRepository;

@Component
public class SessaoDeVotacaoValidator implements EntityValidator<SessaoDeVotacaoForm> {
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired
	private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;
	
	
	@Override
	public void validate(SessaoDeVotacaoForm sessaoDeVotacaoForm) {
		this.validarSessaoExistenteParaPauta(sessaoDeVotacaoForm.getCodigoPauta());
	}

	private void validarSessaoExistenteParaPauta(String codigoPautaAssociada) {
		Optional<Pauta> pauta = pautaRepository.findByCodigo(codigoPautaAssociada);
		Optional<SessaoDeVotacao> sessao = sessaoDeVotacaoRepository.findByPauta(pauta.get());
		
		if (sessao.isPresent()) {
			throw new SessaoDeVotacaoExistenteException(String.format("Já existe uma sessão de votação aberta para a pauta de código %s", codigoPautaAssociada));
		}
	}
}
