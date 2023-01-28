package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.api.dto.form.VotoForm;
import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;
import com.ntconsult.sicredicooperativa.domain.enums.StatusSessaoDeVotacaoEnum;
import com.ntconsult.sicredicooperativa.domain.exception.SessaoDeVotacaoFechadaException;
import com.ntconsult.sicredicooperativa.domain.exception.SessaoDeVotacaoNaoCadastradaException;
import com.ntconsult.sicredicooperativa.domain.repository.SessaoDeVotacaoRepository;

@Component
public class VotoValidator implements EntityValidator<VotoForm>{
	
	@Autowired
	private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;
	
	@Override
	public void validate(VotoForm votoForm) {
		this.validarSessaoAbertaParaVotacao(votoForm.getCodigoSessaoVotacao());
	}
	
	private void validarSessaoAbertaParaVotacao(String codigoDaSessao) {
		Optional<SessaoDeVotacao> sessaoDeVotacao = this.sessaoDeVotacaoRepository.findByCodigoDaSessao(codigoDaSessao);
		if(sessaoDeVotacao.isPresent()) {
			long diferencaEmMilisegundos = Calendar.getInstance().getTime().getTime() - sessaoDeVotacao.get().getDataHoraAbertura().getTime();
			long diferencaEmMinutos = TimeUnit.MINUTES.convert(diferencaEmMilisegundos, TimeUnit.MILLISECONDS);
			
			if(diferencaEmMinutos > sessaoDeVotacao.get().getPrazoFechamentoEmMinutos()) {
				sessaoDeVotacao.get().setStatusSessaoDeVotacao(StatusSessaoDeVotacaoEnum.FECHADA);
				this.sessaoDeVotacaoRepository.save(sessaoDeVotacao.get());
				throw new SessaoDeVotacaoFechadaException("A sessão se encontra encerrada para votação");
			}
		}else {
			throw new SessaoDeVotacaoNaoCadastradaException("A sessão de votação informada ainda não foi aberta");
		}
	}
}
