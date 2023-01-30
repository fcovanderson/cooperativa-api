package com.ntconsult.sicredicooperativa.api.dto.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author vanderson
 * 
 * Classe responsável por agregar as informações do corpo da requisição para o serviço de abertura de uma nova sessão de votação
 *
 */
@Getter
@Setter
public class VotingSessionForm {
	
	@NotNull(message = "Deve ser informado um código para a sessão")
	@NotBlank(message = "O código da sessão não pode ser vazio")
	@Length(min=6, max = 6, message = "O código da sessão deve conter exatamente 6 caracteres")
	private String sessionCode;

	@NotNull(message = "Deve ser informado o código da pauta associada à sessão")
	@NotBlank(message = "O código da pauta não pode ser vazio")
	@Length(min=6, max = 6, message = "O código da pauta deve conter exatamente 6 caracteres")
	private String agendaCode;
	
	@Min(value = 1, message = "O prazo de fechamento da sessão de votação precisa ser igual ou superior a 1 minuto")
	private Integer timeOfClosingInMinutes;
}
