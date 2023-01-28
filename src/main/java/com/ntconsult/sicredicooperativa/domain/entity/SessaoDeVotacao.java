package com.ntconsult.sicredicooperativa.domain.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntconsult.sicredicooperativa.domain.enums.StatusSessaoDeVotacaoEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "ID_PAUTA")
	private Pauta pauta;
	
	@Column(name = "DATA_ABERTURA", columnDefinition="DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataHoraAbertura;
	
	@Column(name="PRAZO_FECHAMENTO")
	private Integer prazoFechamentoEmMinutos;
	
	@Column(name="CODIGO", length = 6)
	private String codigoDaSessao;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS", length = 10)
	private StatusSessaoDeVotacaoEnum statusSessaoDeVotacao;
	
	@JsonIgnore
	@OneToMany(mappedBy="sessaoDeVotacao")
	private List<Voto> votos;
	
	@Column(name="QUANT_VOTOS_SIM")
	private Integer quantVotosSim;
	
	@Column(name="QUANT_VOTOS_NAO")
	private Integer quantVotosNao;
	
	public SessaoDeVotacao() {
		super();
	}

	public SessaoDeVotacao(Pauta pauta, Date dataHoraAbertura, Integer prazoFechamentoEmMinutos, String codigoDaSessao) {
		super();
		this.pauta = pauta;
		this.dataHoraAbertura = dataHoraAbertura;
		this.prazoFechamentoEmMinutos = prazoFechamentoEmMinutos == null ? 1 : prazoFechamentoEmMinutos;
		this.codigoDaSessao = codigoDaSessao;
		this.statusSessaoDeVotacao = StatusSessaoDeVotacaoEnum.ABERTA;
	}
}
