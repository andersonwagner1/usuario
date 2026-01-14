package br.com.prefeitura.usuario.dto2;




public class RetornoDto<T> {
	
	private boolean situacao;
	private String descricao;
	private T resultado;
	
	

	
	public RetornoDto(){
		this.situacao = true;
		this.descricao = "Ação executado com sucesso";
	}


	public boolean getSituacao() {
		return situacao;
	}


	public void setSituacao(boolean situacao) {
		this.situacao = situacao;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.situacao = false;
		this.descricao = descricao;
	}


	public T getResultado() {
		return resultado;
	}


	public void setResultado(T resultado) {
		this.resultado = resultado;
	}


	
}
