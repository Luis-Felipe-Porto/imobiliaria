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

import br.com.api.imobiliaria.model.Aluguel;
import br.com.api.imobiliaria.service.AluguelService;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {
	
	private final AluguelService aluguelService;
	
	@Autowired
	public AluguelController(AluguelService aluguelService) {
		this.aluguelService = aluguelService;
	}
	@GetMapping
	public Page<Aluguel> lista(
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 2)
			Pageable pageable){
			Page<Aluguel> paginaAluguel = aluguelService.buscaCom(pageable);
			return paginaAluguel;
	}

	@PostMapping
	public Aluguel cadastrar(@RequestBody @Valid Aluguel aluguel) {
		return this.aluguelService.salvar(aluguel);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Aluguel> aluguel = this.aluguelService.buscaPor(id);
		if(aluguel.isPresent()) {
			this.aluguelService.deletePor(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Aluguel> buscaPor(@PathVariable Long id){
		Optional<Aluguel> optional = aluguelService.buscaPor(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<Aluguel> atualiza(@PathVariable Long id,@RequestBody Aluguel aluguel){
		Optional<Aluguel> optional = aluguelService.buscaPor(id);
		
		if(optional.isPresent()) {
			aluguel.setId(id);
			Aluguel AluguelAtualizado =  aluguelService.salvar(aluguel);
			return ResponseEntity.ok(AluguelAtualizado);
		}
		return ResponseEntity.notFound().build();
	}
	@PatchMapping("/{id}")
    public ResponseEntity<Aluguel> atualizacaoParcial(@PathVariable Long id,
                                                      @RequestBody Map<String, Object> campos) {
        Optional<Aluguel> optional = aluguelService.buscaPor(id);
        if (optional.isPresent()) {
            Aluguel clienteAtual = optional.get();
            merge(campos, clienteAtual );
            return this.atualiza(id, clienteAtual );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void merge(Map<String, Object> campos, Aluguel aluguelDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Aluguel clienteOrigem = objectMapper.convertValue(campos, Aluguel.class );

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Aluguel.class, nomePropriedade );
            field.setAccessible(true );

            Object novoValor = ReflectionUtils.getField(field, clienteOrigem );

            ReflectionUtils.setField(field, aluguelDestino, novoValor );
        });
    }
}
