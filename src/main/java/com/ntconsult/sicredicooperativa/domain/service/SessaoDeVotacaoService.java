package com.ntconsult.sicredicooperativa.domain.service;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntconsult.sicredicooperativa.api.dto.form.SessaoDeVotacaoForm;
import com.ntconsult.sicredicooperativa.domain.entity.Pauta;
import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;
import com.ntconsult.sicredicooperativa.domain.repository.PautaRepository;
import com.ntconsult.sicredicooperativa.domain.repository.SessaoDeVotacaoRepository;
import com.ntconsult.sicredicooperativa.domain.validator.SessaoDeVotacaoValidator;

@Service
public class SessaoDeVotacaoService implements GenericService<SessaoDeVotacaoForm, SessaoDeVotacao>{
	
	@Autowired 
	private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired 
	private SessaoDeVotacaoValidator validator;
	
	@Override
	public SessaoDeVotacao save(SessaoDeVotacaoForm sessaoDeVotacaoForm) {
	    this.validator.validate(sessaoDeVotacaoForm);
	    Optional<Pauta> pauta = pautaRepository.findByCodigo(sessaoDeVotacaoForm.getCodigoPauta());
		SessaoDeVotacao sessaoDeVotacao = new SessaoDeVotacao(pauta.get(), Calendar.getInstance().getTime(), sessaoDeVotacaoForm.getPrazoFechamentoEmMinutos(), sessaoDeVotacaoForm.getCodigoSessao());
		return this.sessaoDeVotacaoRepository.save(sessaoDeVotacao);
	}
}
