package com.ntconsult.sicredicooperativa.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ntconsult.sicredicooperativa.domain.enums.AgendaStatusEnum;


import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author vanderson
 * 
 * Classe que representa a entidade Pauta com as informações de:
 *   
 *   Código (considera-se que deve conter exatamente 6 caracteres), 
 *   Descrição (campo de texto aberto)
 *   Status (APPROVED OU REJECTED). O campo é definido/atualizado quando há a requisição de contabilizar votos para a sessão associada.
 * 
 */
@Getter
@Setter
@Entity
@Table(name = "AGENDA")
public class Agenda implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID_AGENDA")
	private Long id;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="CODE")
	private String code;
	
	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private AgendaStatusEnum status;
	
	public Agenda() {
	}
	
	public Agenda(String description) {
		super();
		this.description = description;
	}

	public Agenda(String description, String code) {
		super();
		this.description = description;
		this.code = code;
	}
}
