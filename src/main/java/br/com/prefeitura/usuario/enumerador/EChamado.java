package br.com.prefeitura.usuario.enumerador;

public enum EChamado {
	USUARIO_CADASTRAR(ECategoria.USUARIO ,"Cadastrar Usuário", true),
	//USUARIO_OCISOSO_EXONERADO(ECategoria.USUARIO ,"Usuario ocioso/Exonerado", false),
	USUARIO_BLOQUEAR_DESBLOQUEAR(ECategoria.USUARIO ,"Bloquear/Desbloquear usuario", true),
	USUARIO_SETOR(ECategoria.USUARIO ,"Incluir/Remover setor usuario", true),
	USUARIO_PERMISSAO(ECategoria.USUARIO ,"Incliur/Remover permissão usuario",true),
	DUVIDA_LOCALIZAR_PROCESSO(ECategoria.DUVIDA, "Localização dos processo", true),
	FALHA_MATERIALIZACAO(ECategoria.FALHA, "Falha na materialização de documento",true);
	
	
	
	private String descricao;
	private ECategoria categoria;
	private Boolean mostrar;
	

	EChamado(ECategoria categoria, String descricao, boolean mostrar){
		this.descricao = descricao;
		this.categoria = categoria;
		this.mostrar = mostrar;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setChamado(String descricao) {
		this.descricao = descricao;
	}

	public ECategoria getCategoria() {
		return categoria;
	}

	public void setCategoria(ECategoria categoria) {
		this.categoria = categoria;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}

