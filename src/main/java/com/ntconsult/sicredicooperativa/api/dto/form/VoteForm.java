package com.ntconsult.sicredicooperativa.api.dto.form;

import com.ntconsult.sicredicooperativa.domain.enums.VoteEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author vanderson
 * 
 * Classe responsável por agregar as informações do corpo da requisição para o serviço de computação de voto
 *
 */
@Getter
@Setter
public class VoteForm {
	
	private String memberCode;
	private VoteEnum voteType;
	private String votingSessionCode;
}
