package br.com.api.imobiliaria.controllers.validation;

public class ErroDeValidacao {
	private String campo;
	private String mensagem;
	
	public ErroDeValidacao(String campo, String erro) {
		this.campo = campo;
		this.mensagem = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getMensagem() {
		return mensagem;
	}
}
