package com.ntconsult.sicredicooperativa.domain.service;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntconsult.sicredicooperativa.api.dto.form.SessaoDeVotacaoForm;
import com.ntconsult.sicredicooperativa.domain.entity.Pauta;
import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;
import com.ntconsult.sicredicooperativa.domain.enums.StatusPautaEnum;
import com.ntconsult.sicredicooperativa.domain.enums.StatusSessaoDeVotacaoEnum;
import com.ntconsult.sicredicooperativa.domain.enums.VotoEnum;
import com.ntconsult.sicredicooperativa.domain.repository.PautaRepository;
import com.ntconsult.sicredicooperativa.domain.repository.SessaoDeVotacaoRepository;
import com.ntconsult.sicredicooperativa.domain.validator.ContabilizacaoVotosValidator;
import com.ntconsult.sicredicooperativa.domain.validator.EncerramentoSessaoValidator;
import com.ntconsult.sicredicooperativa.domain.validator.SessaoDeVotacaoValidator;

@Service
public class SessaoDeVotacaoService implements GenericService<SessaoDeVotacaoForm, SessaoDeVotacao>{
	
	@Autowired 
	private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Autowired 
	private SessaoDeVotacaoValidator validator;
	
	@Autowired 
	private EncerramentoSessaoValidator encerramentoSessaoValidator;
	
	@Autowired 
	private ContabilizacaoVotosValidator contabilizacaoVotosValidator;
	
	@Override
	public SessaoDeVotacao save(SessaoDeVotacaoForm sessaoDeVotacaoForm) {
	    this.validator.validate(sessaoDeVotacaoForm);
	    Optional<Pauta> pauta = pautaRepository.findByCodigo(sessaoDeVotacaoForm.getCodigoPauta());
		SessaoDeVotacao sessaoDeVotacao = new SessaoDeVotacao(pauta.get(), Calendar.getInstance().getTime(), sessaoDeVotacaoForm.getPrazoFechamentoEmMinutos(), sessaoDeVotacaoForm.getCodigoSessao());
		return this.sessaoDeVotacaoRepository.save(sessaoDeVotacao);
	}
	
	public SessaoDeVotacao encerrarSessao(String codigoSessao) {
		Optional<SessaoDeVotacao> sessaoDeVotacao = sessaoDeVotacaoRepository.findByCodigoDaSessao(codigoSessao);
	    this.encerramentoSessaoValidator.validate(sessaoDeVotacao);
	    sessaoDeVotacao.get().setStatusSessaoDeVotacao(StatusSessaoDeVotacaoEnum.FECHADA);
		return this.sessaoDeVotacaoRepository.save(sessaoDeVotacao.get());
	}
	
	public SessaoDeVotacao contabilizarVotos(String codigoSessao) {
		Optional<SessaoDeVotacao> sessaoDeVotacao = sessaoDeVotacaoRepository.findByCodigoDaSessao(codigoSessao);
	    this.contabilizacaoVotosValidator.validate(sessaoDeVotacao);
	    
	    long quantVotosSim = sessaoDeVotacao.get().getVotos().stream().filter(voto -> voto.getTipoVoto().equals(VotoEnum.SIM)).count();
	    long quantVotosNao = sessaoDeVotacao.get().getVotos().stream().filter(voto -> voto.getTipoVoto().equals(VotoEnum.NAO)).count();
	    
	    sessaoDeVotacao.get().setQuantVotosSim(Integer.valueOf(String.valueOf(quantVotosSim)));
	    sessaoDeVotacao.get().setQuantVotosNao(Integer.valueOf(String.valueOf(quantVotosNao)));
	    
	    sessaoDeVotacao.get().getPauta().setStatus(quantVotosSim > quantVotosNao ? StatusPautaEnum.APROVADA : StatusPautaEnum.REPROVADA);
	    
	    
		return this.sessaoDeVotacaoRepository.save(sessaoDeVotacao.get());
	}
}
