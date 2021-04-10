package br.com.api.imobiliaria.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.imobiliaria.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	   List<Cliente> findByNomeContaining(String nome );
	   Page<Cliente> findByNomeContaining(String nome, Pageable paginacao);
}
