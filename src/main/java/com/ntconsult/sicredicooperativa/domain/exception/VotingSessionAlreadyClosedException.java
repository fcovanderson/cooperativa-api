package com.ntconsult.sicredicooperativa.domain.exception;

public class VotingSessionAlreadyClosedException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VotingSessionAlreadyClosedException(String message) {
		super(message);
	}

}
