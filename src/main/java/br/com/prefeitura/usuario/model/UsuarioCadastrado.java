package br.com.prefeitura.usuario.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USUARIOS_CADASTRADOS")
public class UsuarioCadastrado {
	
	@Id
	private String nrCpf;
	
	private String cdUsuario;
	
	private String dsNome;
	
	private String dsProntuario;
	

	public String getNrCpf() {
		return nrCpf;
	}

	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}

	public String getDsNome() {
		return dsNome;
	}

	public void setDsNome(String dsNome) {
		this.dsNome = dsNome;
	}

	public String getDsProntuario() {
		return dsProntuario;
	}

	public void setDsProntuario(String dsProntuario) {
		this.dsProntuario = dsProntuario;
	}

	public String getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	
	
	
	
	
	
	
	
}
