package com.ntconsult.sicredicooperativa.domain.exception;

public class ClosedVotingSessionException extends BusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClosedVotingSessionException(String mensagem) {
		super(mensagem);
	}
}
