package com.ntconsult.sicredicooperativa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ntconsult.sicredicooperativa.domain.entity.Agenda;

/**
 * 
 * @author vanderson
 * 
 * Classe gerenciada pelo framework spring e responsável por executar as operações em banco de dados para a entidade Agenda
 *
 */
@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{
	
	Optional<Agenda> findByCode(String code);
	
}
