package com.ntconsult.sicredicooperativa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.entity.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{
	
	Optional<Vote> findByAssociadoCodigoAndSessaoDeVotacao(String associadoCodigo, VotingSession sessaoDeVotacao);
}
