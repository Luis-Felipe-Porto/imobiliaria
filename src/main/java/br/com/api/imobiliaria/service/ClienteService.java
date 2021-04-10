package br.com.api.imobiliaria.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.api.imobiliaria.model.Cliente;
import br.com.api.imobiliaria.model.Imovel;
import br.com.api.imobiliaria.repository.ClienteRepository;

@Service
public class ClienteService {
	
	private final ClienteRepository clienteRepository;
	
	@Autowired
	public ClienteService(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		return this.clienteRepository.save(cliente);
	}
	public List<Cliente> todos(){
		return this.clienteRepository.findAll();
	}
	@Transactional
	public void deletePor(Long id) {
		this.clienteRepository.deleteById(id);
	}
	public Optional<Cliente> buscaPor(Long id) {
		return this.clienteRepository.findById(id);
	}
	public List<Cliente> buscaPor(String nome) {
		return this.clienteRepository.findByNomeContaining(nome);
	}
	public Page<Cliente> buscaPor(String nome,Pageable pageable){
		return this.clienteRepository.findByNomeContaining(nome, pageable);
	}
	public Page<Cliente> buscaCom(Pageable pageable) {
		return this.clienteRepository.findAll(pageable);
	}
	public void alugarImovel(Imovel imovel) {
		
	}
}
