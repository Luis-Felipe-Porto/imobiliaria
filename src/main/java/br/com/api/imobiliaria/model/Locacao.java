package br.com.api.imobiliaria.model;


import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Locacao {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade=CascadeType.ALL)
	private Imovel imovel;
	@ManyToOne(cascade=CascadeType.ALL)
	private Cliente inquilino; 
	private boolean ativo;
	@NotNull @DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataInicioAluguel;
	@NotNull @DateTimeFormat(pattern="dd/MM/yyyy")
	private Date dataFimAluguel;
	@NotNull
	private Integer diaVencimento;
	@NotNull
	private Double percentualMulta;
	@NotNull
	private Double valorAluguel;
	private String observacao;
	
	public Integer getDataVencimento() {
		return diaVencimento;
	}

	public String getObservacao() {
		return observacao;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public Cliente getInquilino() {
		return inquilino;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public Date getDataInicioAluguel() {
		return dataInicioAluguel;
	}

	public Date getDataFimAluguel() {
		return dataFimAluguel;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public Double getPercentualMulta() {
		return percentualMulta;
	}

	public Double getValorAluguel() {
		return valorAluguel;
	}
	public void receberInquilino(Cliente cliente) {
		this.inquilino = cliente;
	}
	
}
