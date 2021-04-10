package br.com.api.imobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.imobiliaria.model.Aluguel;

public interface AluguelRepository extends JpaRepository<Aluguel, Long>{

}
