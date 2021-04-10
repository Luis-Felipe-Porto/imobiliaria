package br.com.api.imobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.imobiliaria.model.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long>{

}
