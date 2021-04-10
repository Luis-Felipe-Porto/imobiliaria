package br.com.api.imobiliaria.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Imovel {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull @NotEmpty
	private String tipoImovel;
	@NotNull @NotEmpty
	private String endereco;
	@NotNull @NotEmpty
	private String Cep;
	private int dormitorio;
	private int banheiro;
	private int suites;
	private int metragem;
	@NotNull @NotEmpty
	private Double valorDeAluguelSugerido;
	private String observacao;
	
	public String getTipoImovel() {
		return tipoImovel;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEndereco() {
		return endereco;
	}
	public String getCep() {
		return Cep;
	}
	public int getDormitorio() {
		return dormitorio;
	}
	public int getBanheiro() {
		return banheiro;
	}
	public int getSuites() {
		return suites;
	}
	public int getMetragem() {
		return metragem;
	}
	public Double getValorDeAluguelSugerido() {
		return valorDeAluguelSugerido;
	}
	public String getObservacao() {
		return observacao;
	}
	public void reajusteDeAluguel(Double novoValorDeAluguel) {
		this.valorDeAluguelSugerido = novoValorDeAluguel;
	}
	
}
