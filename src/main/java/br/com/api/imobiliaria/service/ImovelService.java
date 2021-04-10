package br.com.api.imobiliaria.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.imobiliaria.model.Imovel;
import br.com.api.imobiliaria.repository.ImovelRepository;

@Service
public class ImovelService {
	
	private ImovelRepository imovelRepository;
	
	@Autowired
	public ImovelService(ImovelRepository imovelRepository) {
		this.imovelRepository = imovelRepository;
	}
	
	@Transactional
	public Imovel salvar(Imovel imovel) {
		return this.imovelRepository.save(imovel);
	}
	@Transactional
	public void deletePor(Long id) {
		this.imovelRepository.deleteById(id);
	}
	public Optional<Imovel> buscaPor(Long id) {
		return this.imovelRepository.findById(id);
	}
	public Page<Imovel> buscaCom(Pageable pageable) {
		return this.imovelRepository.findAll(pageable);
	}
	
}
