package com.ntconsult.sicredicooperativa.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ntconsult.sicredicooperativa.domain.enums.VoteEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author vanderson
 *
 * Classe que representa a entidade Voto com as informações de:
 *   
 *   Código do associado realizou o voto (considera-se apenas o código do associado. Dessa forma, não há um relacionamento com outra entidade que represente um associado), 
 *   Voto (YES ou NO)
 *   Sessão a qual está associado  
 */
@Entity
@Getter
@Setter
@Table(name = "VOTE")
public class Vote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="ID_VOTO")
	private Long id;
	
	@Column(name = "MEMBER_CODE")
	private String memberCode;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "VOTE_TYPE")
	private VoteEnum voteType;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VOTING_SESSION")
	private VotingSession votingSession;
	
	public Vote() {
		super();
	}
	
	public Vote(String memberCode, VoteEnum vote) {
		super();
		this.memberCode = memberCode;
		this.voteType = vote;
	}

	public Vote(String memberCode, VoteEnum voteType, VotingSession votingSession) {
		super();
		this.memberCode = memberCode;
		this.voteType = voteType;
		this.votingSession = votingSession;
	}
}
