package com.ntconsult.sicredicooperativa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ntconsult.sicredicooperativa.domain.entity.Pauta;
import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;

@Repository
public interface SessaoDeVotacaoRepository extends JpaRepository<SessaoDeVotacao, Long>{
	Optional<SessaoDeVotacao> findByPauta(Pauta pauta);
}
