package br.com.api.imobiliaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.imobiliaria.model.Imovel;
import br.com.api.imobiliaria.repository.imovel.ImovelRepositoryQuery;

public interface ImovelRepository extends JpaRepository<Imovel, Long>,ImovelRepositoryQuery{

}
