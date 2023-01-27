package com.ntconsult.sicredicooperativa.domain.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PAUTA")
public class Pauta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID_PAUTA")
	private Long id;
	
	@Column(name="DESCRICAO")
	private String descricao;
	
	@Column(name="CODIGO")
	private String codigo;
	
	public Pauta() {
	}
	
	public Pauta(String descricao) {
		super();
		this.descricao = descricao;
	}

	public Pauta(String descricao, String codigo) {
		super();
		this.descricao = descricao;
		this.codigo = codigo;
	}
}
