package com.ntconsult.sicredicooperativa.domain.repository;

import com.ntconsult.sicredicooperativa.domain.exception.NegocioException;

public class SessaoDeVotacaoJaAssociadaException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessaoDeVotacaoJaAssociadaException(String mensagem) {
		super(mensagem);
	}
}
