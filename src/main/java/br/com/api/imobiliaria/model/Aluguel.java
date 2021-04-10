package br.com.api.imobiliaria.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Aluguel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private LocalDate dataVencimento;
	@NotNull @NotEmpty
	private Double valorPago;
	@ManyToOne(cascade=CascadeType.ALL)
	private Locacao locacao;
	private String observacao;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDataVencimento() {
		return dataVencimento;
	}
	public Double getValorPago() {
		return valorPago;
	}
	public String getObservacao() {
		return observacao;
	}
	
	
}
