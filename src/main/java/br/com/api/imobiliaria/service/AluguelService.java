package br.com.api.imobiliaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.api.imobiliaria.model.Aluguel;
import br.com.api.imobiliaria.repository.AluguelRepository;

@Service
public class AluguelService {
	private final AluguelRepository aluguelRepository;
	
	@Autowired
	public AluguelService(AluguelRepository aluguelRepository) {
		this.aluguelRepository = aluguelRepository;
	}
	
	@Transactional
	public Aluguel salvar(Aluguel aluguel) {
		return this.aluguelRepository.save(aluguel);
	}
	public List<Aluguel> todos(){
		return this.aluguelRepository.findAll();
	}
	@Transactional
	public void deletePor(Long id) {
		this.aluguelRepository.deleteById(id);
	}
	public Optional<Aluguel> buscaPor(Long id) {
		return this.aluguelRepository.findById(id);
	}
	public Page<Aluguel> buscaCom(Pageable pageable) {
		return this.aluguelRepository.findAll(pageable);
	}
	

}
