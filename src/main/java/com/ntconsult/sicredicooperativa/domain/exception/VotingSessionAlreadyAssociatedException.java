package com.ntconsult.sicredicooperativa.domain.exception;

public class VotingSessionAlreadyAssociatedException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VotingSessionAlreadyAssociatedException(String message) {
		super(message);
	}
}
