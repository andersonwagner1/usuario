package br.com.prefeitura.usuario.main.exonerados2;

public class UsuarioDesativadoDTO {
    private  String matricula;
    private  String nome;
    private  String cpf;
    private  String icTipo;

    public UsuarioDesativadoDTO(String matricula, String nome, String cpf, String icTipo) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.icTipo = icTipo;
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

	public String getIcTipo() {
		return icTipo;
	}

	public void setIcTipo(String icTipo) {
		this.icTipo = icTipo;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
    
    
}
