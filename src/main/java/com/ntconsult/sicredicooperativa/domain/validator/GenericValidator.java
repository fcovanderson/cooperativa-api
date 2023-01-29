package com.ntconsult.sicredicooperativa.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class GenericValidator{
	
	@Autowired
	private MessageSource messageSource;

}
