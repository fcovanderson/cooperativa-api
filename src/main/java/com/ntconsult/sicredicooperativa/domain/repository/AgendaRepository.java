package com.ntconsult.sicredicooperativa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ntconsult.sicredicooperativa.domain.entity.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>{
	
	Optional<Agenda> findByCode(String code);
	
}
