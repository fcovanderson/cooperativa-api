package com.ntconsult.sicredicooperativa.api.dto.form;

import com.ntconsult.sicredicooperativa.domain.enums.VotoEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotoForm {
	
	private String associadoCodigo;
	private VotoEnum tipoVoto;
	private String codigoSessaoVotacao;
}
