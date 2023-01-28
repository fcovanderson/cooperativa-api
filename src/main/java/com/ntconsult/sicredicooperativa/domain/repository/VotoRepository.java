package com.ntconsult.sicredicooperativa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntconsult.sicredicooperativa.domain.entity.Pauta;
import com.ntconsult.sicredicooperativa.domain.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long>{
	
	Optional<Pauta> findByAssociadoCodigo(String associadoCodigo);
}
