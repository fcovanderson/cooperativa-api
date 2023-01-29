package com.ntconsult.sicredicooperativa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;
import com.ntconsult.sicredicooperativa.domain.entity.Vote;

/**
 * 
 * @author vanderson
 * 
 * Classe gerenciada pelo framework spring e responsável por executar as operações em banco de dados para a entidade Vote
 *
 */
@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{
	
	Optional<Vote> findByMemberCodeAndVotingSession (String memberCode, VotingSession votingSession);
}
