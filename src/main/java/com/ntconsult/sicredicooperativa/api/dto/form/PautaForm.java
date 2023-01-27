package com.ntconsult.sicredicooperativa.api.dto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PautaForm {
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String descricao;
}
