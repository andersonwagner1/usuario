package br.com.prefeitura.usuario.main.exonerados;

public class UsuarioDTO {
    private final String matricula;
    private final String nome;
    private final String cpf;

    public UsuarioDTO(String matricula, String nome, String cpf) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
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
