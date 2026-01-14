package br.com.prefeitura.usuario.dto2;

public class Interessado {
	public String nome;
	public String documento;
	public String principal;
	
	
	

	public String getNome() {
		return nome;
	}




	public void setNome(String nome) {
		this.nome = nome;
	}




	public String getDocumento() {
		return documento;
	}




	public void setDocumento(String documento) {
		this.documento = documento;
	}




	public String getPrincipal() {
		return principal;
	}




	public void setPrincipal(String principal) {
		this.principal = principal;
	}




	@Override
	public String toString() {
		return "Interessado{" + "nome='" + nome + '\'' + ", documento='"
				+ documento + '\'' + ", principal='" + principal + '\'' + '}';
	}
}
