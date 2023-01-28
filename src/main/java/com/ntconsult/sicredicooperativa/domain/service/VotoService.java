package com.ntconsult.sicredicooperativa.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ntconsult.sicredicooperativa.api.dto.form.VotoForm;
import com.ntconsult.sicredicooperativa.domain.entity.SessaoDeVotacao;
import com.ntconsult.sicredicooperativa.domain.entity.Voto;
import com.ntconsult.sicredicooperativa.domain.repository.SessaoDeVotacaoRepository;
import com.ntconsult.sicredicooperativa.domain.repository.VotoRepository;

@Service
public class VotoService implements GenericService<VotoForm, Voto>{
	
	@Autowired 
	private VotoRepository votoRepository;
	
	@Autowired 
	private SessaoDeVotacaoRepository sessaoVotacaoRepository;
	
	@Override
	public Voto save(VotoForm votoForm) {
		SessaoDeVotacao sessaoDeVotacao = sessaoVotacaoRepository.findByCodigoDaSessao(votoForm.getCodigoSessaoVotacao()).get();
		Voto novoVoto = new Voto(votoForm.getAssociadoCodigo(), votoForm.getTipoVoto(), sessaoDeVotacao);
		return votoRepository.save(novoVoto);
	}
}
