package br.com.api.imobiliaria.controllers;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.data.domain.Sort;
import br.com.api.imobiliaria.model.Cliente;
import br.com.api.imobiliaria.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	@PostMapping
	public Cliente cadastro(@Valid @RequestBody Cliente cliente){
		final Cliente clienteSalvo = clienteService.salvar(cliente);
		return clienteSalvo;
	}
	/*
	@GetMapping
	public List<Cliente> lista(){
		return clienteService.todos();
	}
	*/
	@GetMapping
	public Page<Cliente> lista(@RequestParam(required=false)String nome,
			@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 2)
			Pageable pageable){
		if(nome == null) {
			Page<Cliente> paginaCliente = clienteService.buscaCom(pageable);
			return paginaCliente;
		}else {
			Page<Cliente> paginaCliente = clienteService.buscaPor(nome,pageable);
			return paginaCliente;
		}
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Cliente> cliente = clienteService.buscaPor(id);
		if(cliente.isPresent()) {
			this.clienteService.deletePor(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscaPor(@PathVariable Long id){
		Optional<Cliente> optional = clienteService.buscaPor(id);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualiza(@PathVariable Long id,@RequestBody Cliente cliente){
		Optional<Cliente> optional = clienteService.buscaPor(id);
		
		if(optional.isPresent()) {
			cliente.setId(id);
			Cliente clienteAtualizado =  clienteService.salvar(cliente);
			return ResponseEntity.ok(clienteAtualizado);
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PatchMapping("/{id}")
    public ResponseEntity<Cliente> atualizacaoParcial(@PathVariable Long id,
                                                      @RequestBody Map<String, Object> campos) {
        Optional<Cliente> optional = clienteService.buscaPor(id);
        if (optional.isPresent()) {
            Cliente clienteAtual = optional.get();
            merge(campos, clienteAtual );
            return this.atualiza(id, clienteAtual );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void merge(Map<String, Object> campos, Cliente clienteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Cliente clienteOrigem = objectMapper.convertValue(campos, Cliente.class );

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Cliente.class, nomePropriedade );
            field.setAccessible(true );

            Object novoValor = ReflectionUtils.getField(field, clienteOrigem );

            ReflectionUtils.setField(field, clienteDestino, novoValor );
        });
    }
}
