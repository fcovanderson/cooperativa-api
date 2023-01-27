package com.ntconsult.sicredicooperativa.domain.service;

import com.ntconsult.sicredicooperativa.api.dto.form.PautaForm;
import com.ntconsult.sicredicooperativa.domain.entity.Pauta;


public interface GenericService {
	 Pauta save(PautaForm pauta);
}
