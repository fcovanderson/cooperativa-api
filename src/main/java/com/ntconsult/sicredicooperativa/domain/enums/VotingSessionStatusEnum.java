package com.ntconsult.sicredicooperativa.domain.enums;

import lombok.Getter;

@Getter
public enum VotingSessionStatusEnum {
	
	OPEN("Open"), CLOSED("Closed");
	
	private String description;

	private VotingSessionStatusEnum(String description) {
		this.description = description;
	}
}
