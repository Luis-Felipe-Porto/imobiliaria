package br.com.api.imobiliaria.controllers;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

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

import br.com.api.imobiliaria.model.Locacao;
import br.com.api.imobiliaria.service.LocacaoService;

@RestController
@RequestMapping("/locacao")
public class LocacaoController {
	
	private final LocacaoService locacaoService;
	
	@Autowired
	public LocacaoController(LocacaoService locacaoService) {
		this.locacaoService = locacaoService;
	}
	@GetMapping
	public Page<Locacao> listar(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 2)
	Pageable pageable){
		return this.locacaoService.buscaCom(pageable);
	}
	@PostMapping
	public Locacao cadastro(@RequestBody Locacao locacao) {
		return this.locacaoService.salvar(locacao);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable Long id){
		Optional<Locacao> optional = this.locacaoService.buscaPor(id);
		if(optional.isPresent()) {
			this.locacaoService.deletePor(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<Locacao> atualiza(@PathVariable Long id,Locacao locacao){
		Optional<Locacao> optional = this.locacaoService.buscaPor(id);
		if(optional.isPresent()) {
			locacao.setId(id);
			Locacao locacaoAtualizada = this.locacaoService.salvar(locacao);
			return ResponseEntity.ok(locacaoAtualizada);
		}
		return ResponseEntity.notFound().build();
	}
	@PatchMapping("/{id}")
    public ResponseEntity<Locacao> atualizacaoParcial(@PathVariable Long id,
                                                      @RequestBody Map<String, Object> campos) {
        Optional<Locacao> optional = this.locacaoService.buscaPor(id);
        if (optional.isPresent()) {
            Locacao locacaoAtual = optional.get();
            merge(campos, locacaoAtual );
            return this.atualiza(id, locacaoAtual );
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	@GetMapping("/{id}")
	public ResponseEntity<Locacao> buscaPor(@PathVariable Long id) {
		Optional<Locacao> optional = this.locacaoService.buscaPor(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
    private void merge(Map<String, Object> campos, Locacao locacaoDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Locacao clienteOrigem = objectMapper.convertValue(campos, Locacao.class );

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Locacao.class, nomePropriedade );
            field.setAccessible(true );

            Object novoValor = ReflectionUtils.getField(field, clienteOrigem );

            ReflectionUtils.setField(field, locacaoDestino, novoValor );
        });
    }
}
