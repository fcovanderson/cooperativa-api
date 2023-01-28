package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Calendar;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.api.dto.form.VotoForm;
import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;
import com.ntconsult.sicredicooperativa.domain.entity.Voto;
import com.ntconsult.sicredicooperativa.domain.enums.StatusSessaoDeVotacaoEnum;
import com.ntconsult.sicredicooperativa.domain.exception.SessaoDeVotacaoFechadaException;
import com.ntconsult.sicredicooperativa.domain.exception.SessaoDeVotacaoNaoCadastradaException;
import com.ntconsult.sicredicooperativa.domain.exception.VotoJaComputadoException;
import com.ntconsult.sicredicooperativa.domain.repository.SessaoDeVotacaoRepository;
import com.ntconsult.sicredicooperativa.domain.repository.VotoRepository;

@Component
public class VotoValidator implements EntityValidator<VotoForm>{
	
	@Autowired
	private SessaoDeVotacaoRepository sessaoDeVotacaoRepository;
	
	@Autowired
	private VotoRepository votoRepository;
	
	@Override
	public void validate(VotoForm votoForm) {
		Optional<SessaoDeVotacao> sessaoDeVotacao = this.sessaoDeVotacaoRepository.findByCodigoDaSessao(votoForm.getCodigoSessaoVotacao());
		this.validarSessaoAbertaParaVotacao(sessaoDeVotacao);
		this.validarVotoJaComputado(votoForm.getAssociadoCodigo(), sessaoDeVotacao);
	}
	
	private void validarSessaoAbertaParaVotacao(Optional<SessaoDeVotacao> sessaoDeVotacao) {
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
	
	private void validarVotoJaComputado(String associadoCodigo, Optional<SessaoDeVotacao> sessaoDeVotacao) {
		Optional<Voto> voto = this.votoRepository.findByAssociadoCodigoAndSessaoDeVotacao(associadoCodigo, sessaoDeVotacao.get());
		if(voto.isPresent()) {
			throw new VotoJaComputadoException(String.format("O associado %s já realizou votação para a sessão %s", associadoCodigo, sessaoDeVotacao.get().getCodigoDaSessao()));
		}
	}
}
