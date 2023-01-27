package com.ntconsult.sicredicooperativa.api.dto.form;


import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaForm {
	
	@NotNull(message = "Deve ser informada uma descrição para a pauta")
	@NotBlank(message = "A descrição da pauta não pode ser vazia")
	private String descricao;
	
	
	@NotNull(message = "Deve ser informado um código para a pauta")
	@NotBlank(message = "O código da pauta não pode ser vazio")
	@Length(min=6, max = 6, message = "O código da pauta deve conter exatamente 6 caracteres")
	private String codigo;
}
