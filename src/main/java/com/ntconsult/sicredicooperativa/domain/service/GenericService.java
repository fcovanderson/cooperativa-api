package com.ntconsult.sicredicooperativa.domain.service;

public interface GenericService<T, E> {
	 
	E save(T entity);
}
