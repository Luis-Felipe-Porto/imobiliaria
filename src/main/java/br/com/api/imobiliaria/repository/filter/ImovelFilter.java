package br.com.api.imobiliaria.repository.filter;

public class ImovelFilter {
	
	private String tipoImovel;

	private Double valorDeAluguelSugerido;
	
	public String getTipoImovel() {
		return tipoImovel;
	}
	public void setTipoImovel(String tipoImovel) {
		this.tipoImovel = tipoImovel;
	}
	public Double getValorDeAluguelSugerido() {
		return valorDeAluguelSugerido;
	}
	public void setValorDeAluguelSugerido(Double valorDeAluguelSugerido) {
		this.valorDeAluguelSugerido = valorDeAluguelSugerido;
	}
	
}
