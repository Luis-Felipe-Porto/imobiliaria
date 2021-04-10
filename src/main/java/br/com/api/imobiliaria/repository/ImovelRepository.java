package br.com.api.imobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.imobiliaria.model.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Long>{

}
