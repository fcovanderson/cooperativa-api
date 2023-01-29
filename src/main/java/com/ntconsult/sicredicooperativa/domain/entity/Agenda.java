package com.ntconsult.sicredicooperativa.domain.entity;

import java.io.Serializable;

import com.ntconsult.sicredicooperativa.domain.enums.AgendaStatusEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author vanderson
 * 
 * Classe que representa a entidade Pauta
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
