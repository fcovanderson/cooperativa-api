package com.ntconsult.sicredicooperativa.domain.repository;

import com.ntconsult.sicredicooperativa.domain.exception.NegocioException;

public class SessaoDeVotacaoExistenteException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SessaoDeVotacaoExistenteException(String mensagem) {
		super(mensagem);
	}
}
