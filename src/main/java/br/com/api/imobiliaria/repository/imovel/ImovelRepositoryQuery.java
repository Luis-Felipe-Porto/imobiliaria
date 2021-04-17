package br.com.api.imobiliaria.repository.imovel;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.api.imobiliaria.model.Imovel;
import br.com.api.imobiliaria.repository.filter.ImovelFilter;



public interface ImovelRepositoryQuery {
	Page<Imovel> filtrar(ImovelFilter filtro, Pageable pageable);
}
