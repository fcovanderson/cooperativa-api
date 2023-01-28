package com.ntconsult.sicredicooperativa.api.dto.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessaoDeVotacaoForm {
	
	@NotNull(message = "Deve ser informado um código para a sessão")
	@NotBlank(message = "O código da sessão não pode ser vazio")
	@Length(min=6, max = 6, message = "O código da sessão deve conter exatamente 6 caracteres")
	private String codigoSessao;

	@NotNull(message = "Deve ser informado o código da pauta associada à sessão")
	@NotBlank(message = "O código da pauta não pode ser vazio")
	@Length(min=6, max = 6, message = "O código da pauta deve conter exatamente 6 caracteres")
	private String codigoPauta;
	
	@Min(value = 1, message = "O prazo de fechamento da sessão de votação precisa ser igual ou superior a 1 minuto")
	private Integer prazoFechamentoEmMinutos;
}
