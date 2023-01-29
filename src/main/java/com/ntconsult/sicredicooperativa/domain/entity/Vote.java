package com.ntconsult.sicredicooperativa.domain.entity;

import java.io.Serializable;

import com.ntconsult.sicredicooperativa.domain.enums.VoteEnum;

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

/**
 * 
 * @author vanderson
 *
 * Classe que representa a entidade Voto
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
