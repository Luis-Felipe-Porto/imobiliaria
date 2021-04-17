package br.com.api.imobiliaria.controllers;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.ObjectMapper;


import br.com.api.imobiliaria.model.Imovel;
import br.com.api.imobiliaria.repository.filter.ImovelFilter;
import br.com.api.imobiliaria.service.ImovelService;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
	
	private final ImovelService imovelService;
	
	@Autowired
	public ImovelController(ImovelService imovelService) {
		this.imovelService = imovelService;
	} 
	@GetMapping("/pesquisa")
    public Page<Imovel> buscaFiltrada(ImovelFilter filtro, Pageable page  ) {
        return imovelService.busca(filtro, page );

   }
	@GetMapping
	public Page<Imovel>listar(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 2)
	Pageable pageable){
		return this.imovelService.buscaCom(pageable);
	}
	@PostMapping
	public Imovel cadastrar(@RequestBody @Valid Imovel imovel){
		return this.imovelService.salvar(imovel);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		Optional<Imovel> imovel = this.imovelService.buscaPor(id);
		if(imovel.isPresent()) {
			this.imovelService.deletePor(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<Imovel> atualiza(@PathVariable Long id,Imovel imovel){
		Optional<Imovel> optional = this.imovelService.buscaPor(id);
		if(optional.isPresent()) {
			imovel.setId(id);
			Imovel imovelAtualizado = this.imovelService.salvar(imovel);
			ResponseEntity.ok(imovelAtualizado);
		}
		return ResponseEntity.notFound().build();
	}
	@PatchMapping("/{id}")
	public ResponseEntity<Imovel> atualizacaoParcial(@PathVariable Long id, @RequestBody Map<String,Object> campos){
		Optional<Imovel> optional = this.imovelService.buscaPor(id);
		if(optional.isPresent()) {
			Imovel imovelAtual = optional.get();
			this.merge(campos,imovelAtual);
			return this.atualiza(id, imovelAtual);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	private void merge(Map<String, Object> campos, Imovel imovelDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Imovel clienteOrigem = objectMapper.convertValue(campos, Imovel.class );

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Imovel.class, nomePropriedade );
            field.setAccessible(true );
            Object novoValor = ReflectionUtils.getField(field, clienteOrigem );
            ReflectionUtils.setField(field, imovelDestino, novoValor );
        });
    }
}
