package com.ntconsult.sicredicooperativa.domain.validator;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;
import com.ntconsult.sicredicooperativa.domain.enums.StatusSessaoDeVotacaoEnum;
import com.ntconsult.sicredicooperativa.domain.exception.SessaoDeVotacaoEmAbertoException;
import com.ntconsult.sicredicooperativa.domain.exception.VotosJaContabilizadosException;

@Component
public class ContabilizacaoVotosValidator implements EntityValidator<Optional<SessaoDeVotacao>>{
	
	@Autowired
	private EncerramentoSessaoValidator encerramentoSessaoValidator;
	
	@Override
	public void validate(Optional<SessaoDeVotacao> sessaoDeVotacao) {
		this.encerramentoSessaoValidator.validarSessaoExistente(sessaoDeVotacao);
		this.validarVotosJaContabilizados(sessaoDeVotacao);
		this.validarSessaoAindaNaoEncerrada(sessaoDeVotacao);
	}
	
	private void validarVotosJaContabilizados(Optional<SessaoDeVotacao> sessaoDeVotacao){
		if(Objects.nonNull(sessaoDeVotacao.get().getPauta().getStatus())) {
			throw new VotosJaContabilizadosException(String.format("Os votos para a sessão de votação %s já foram contabilizados", sessaoDeVotacao.get().getCodigoDaSessao()));
		}
	}
	
	private void validarSessaoAindaNaoEncerrada(Optional<SessaoDeVotacao> sessaoDeVotacao) {
		if(sessaoDeVotacao.get().getStatusSessaoDeVotacao().equals(StatusSessaoDeVotacaoEnum.ABERTA)) {
			throw new SessaoDeVotacaoEmAbertoException(String.format("A sessão de votação %s ainda está em aberto", sessaoDeVotacao.get().getCodigoDaSessao()));
		}
	}
}
