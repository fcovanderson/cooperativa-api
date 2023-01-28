package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;
import com.ntconsult.sicredicooperativa.domain.enums.StatusSessaoDeVotacaoEnum;
import com.ntconsult.sicredicooperativa.domain.exception.SessaoDeVotacaoJaEncerradaException;
import com.ntconsult.sicredicooperativa.domain.exception.SessaoDeVotacaoNaoCadastradaException;

@Component
public class EncerramentoSessaoValidator implements EntityValidator<Optional<SessaoDeVotacao>>{
	
	@Override
	public void validate(Optional<SessaoDeVotacao> sessaoDeVotacao) {
		this.validarSessaoExistente(sessaoDeVotacao);
		this.validarSessaoEncerrada(sessaoDeVotacao);
	}
	
	public void validarSessaoExistente(Optional<SessaoDeVotacao> sessaoDeVotacao) {
		if(sessaoDeVotacao.isEmpty()) {
			throw new SessaoDeVotacaoNaoCadastradaException("A sessão de votação informada ainda não foi aberta");
		}
	}
	
	private void validarSessaoEncerrada(Optional<SessaoDeVotacao> sessaoDeVotacao) {
		if(sessaoDeVotacao.get().getStatusSessaoDeVotacao().equals(StatusSessaoDeVotacaoEnum.FECHADA)) {
			throw new SessaoDeVotacaoJaEncerradaException(String.format("A sessão de votação %s já foi encerrada anteriormente", sessaoDeVotacao.get().getCodigoDaSessao()));
		}
	}
}
