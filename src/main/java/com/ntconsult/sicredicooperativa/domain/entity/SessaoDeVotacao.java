package com.ntconsult.sicredicooperativa.domain.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "SESSAO_VOTACAO")
public class SessaoDeVotacao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID_SESSAO_VOTACAO")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "ID_PAUTA")
	private Pauta pauta;
	
	@Column(name = "DATA_ABERTURA", columnDefinition="DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraAbertura;
	
	@Column(name="PRAZO_FECHAMENTO")
	private Integer prazoFechamentoEmMinutos;
	
	@Column(name="COD_SESSAO_VOTACAO", length = 6)
	private String codigoDaSessao;
	
	public SessaoDeVotacao() {
		super();
	}

	public SessaoDeVotacao(Pauta pauta, Date dataHoraAbertura, Integer prazoFechamentoEmMinutos, String codigoDaSessao) {
		super();
		this.pauta = pauta;
		this.dataHoraAbertura = dataHoraAbertura;
		this.prazoFechamentoEmMinutos = prazoFechamentoEmMinutos == null ? 1 : prazoFechamentoEmMinutos;
		this.codigoDaSessao = codigoDaSessao;
	}
}
