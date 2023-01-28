package com.ntconsult.sicredicooperativa.domain.entity;

import java.io.Serializable;

import com.ntconsult.sicredicooperativa.domain.enums.VotoEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "VOTO")
public class Voto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID_VOTO")
	private Long id;
	
	@Column(name = "ASSOCIADO_CODIGO")
	private String associadoCodigo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TIPO_VOTO")
	private VotoEnum tipoVoto;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SESSAO_VOTACAO")
	private SessaoDeVotacao sessaoDeVotacao;
	
	public Voto() {
		super();
	}
	
	public Voto(String associadoCodigo, VotoEnum tipoVoto) {
		super();
		this.associadoCodigo = associadoCodigo;
		this.tipoVoto = tipoVoto;
	}

	public Voto(String associadoCodigo, VotoEnum tipoVoto, SessaoDeVotacao sessaoDeVotacao) {
		super();
		this.associadoCodigo = associadoCodigo;
		this.tipoVoto = tipoVoto;
		this.sessaoDeVotacao = sessaoDeVotacao;
	}
}
