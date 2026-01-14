package br.com.prefeitura.usuario.enumerador;

public enum ECategoria {
	USUARIO("Usuário"), PERMISSAO("Permissão"), PROCESSO("Processo"), DUVIDA("Dúvida"), FALHA("Falha");
	
	private String descricao;
	
	

	 ECategoria(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
