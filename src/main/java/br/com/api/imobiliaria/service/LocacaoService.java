package br.com.api.imobiliaria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.api.imobiliaria.model.Locacao;
import br.com.api.imobiliaria.repository.LocacaoRepository;

@Service
public class LocacaoService {
	
	private LocacaoRepository locacaoRepository;
	
	@Autowired
	public LocacaoService(LocacaoRepository locacaoRepository) {
		this.locacaoRepository = locacaoRepository;
	}
	@Transactional
	public Locacao salvar(Locacao aluguel) {
		return this.locacaoRepository.save(aluguel);
	}
	@Transactional
	public void deletePor(Long id) {
		this.locacaoRepository.deleteById(id);
	}
	public Optional<Locacao> buscaPor(Long id) {
		return this.locacaoRepository.findById(id);
	}
	public Page<Locacao> buscaCom(Pageable pageable) {
		return this.locacaoRepository.findAll(pageable);
	}
}
