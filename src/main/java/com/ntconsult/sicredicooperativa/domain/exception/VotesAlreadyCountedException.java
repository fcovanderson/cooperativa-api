package com.ntconsult.sicredicooperativa.domain.exception;

public class VotesAlreadyCountedException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VotesAlreadyCountedException(String messagem) {
		super(messagem);
	}

}
