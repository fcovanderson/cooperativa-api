package com.ntconsult.sicredicooperativa.domain.enums;

import lombok.Getter;

@Getter
public enum StatusSessaoDeVotacaoEnum {
	
	ABERTA("Aberta"), FECHADA("Fechada");
	
	private String descricao;

	private StatusSessaoDeVotacaoEnum(String descricao) {
		this.descricao = descricao;
	}
}
