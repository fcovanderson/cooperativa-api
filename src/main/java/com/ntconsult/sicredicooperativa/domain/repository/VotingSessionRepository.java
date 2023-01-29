package com.ntconsult.sicredicooperativa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntconsult.sicredicooperativa.domain.entity.Agenda;
import com.ntconsult.sicredicooperativa.domain.entity.VotingSession;


/**
 * 
 * @author vanderson
 * 
 * Classe gerenciada pelo framework spring e responsável por executar as operações em banco de dados para a entidade VotingSession
 *
 */
@Repository
public interface VotingSessionRepository extends JpaRepository<VotingSession, Long>{
	
	Optional<VotingSession> findByAgenda(Agenda agenda);
	
	Optional<VotingSession> findBySessionCode(String sessionCode);
}
