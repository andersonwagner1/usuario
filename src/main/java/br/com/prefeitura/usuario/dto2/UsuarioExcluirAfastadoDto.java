package br.com.prefeitura.usuario.dto2;

public class UsuarioExcluirAfastadoDto {
	private final String matricula;
	private final String nome;
	private final String cpf;

	public UsuarioExcluirAfastadoDto(String m, String n, String c) {
		this.matricula = m;
		this.nome = n;
		this.cpf = c;
	}

	public String getMatricula() {
		return matricula;
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}
}