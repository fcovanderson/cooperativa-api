package com.ntconsult.sicredicooperativa.domain.validator;

public interface EntityValidator<T>{
	void validate(T entity);
}
