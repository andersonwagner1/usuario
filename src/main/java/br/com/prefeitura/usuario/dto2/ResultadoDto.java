package br.com.prefeitura.usuario.dto2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class ResultadoDto<T>{
	private T objeto;
	private boolean sucesso;
	private String resultado = "sucesso";
	public T getObjeto() {
		return objeto;
	}
	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}
	public boolean isSucesso() {
		return sucesso;
	}
	public void setSucesso(boolean sucesso) {
		this.sucesso = sucesso;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	
	

}
