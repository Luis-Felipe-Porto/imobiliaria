package br.com.api.imobiliaria.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull @NotEmpty @Length(min = 3, max = 200)
	private String nome;
	@CPF
	private String cpf;
	@NotEmpty @Length(min = 8, message = "Deve possuir {min} dígitos no mínimo")
	private String telefone;
	@Email @NotEmpty
	private String email;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate dataNascimento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getEmail() {
		return email;
	}
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}
	public String getTelefone() {
		return telefone;
	}
	public void alugar(Imovel imovel) {
		
	}
	public void pagarAluguel(Imovel imovel) {
		
	}
	public void devolverImovel(Imovel imovel) {
		
	}
	
}
