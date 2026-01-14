package br.com.prefeitura.usuario.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USUARIOS_DESATIVADO")
public class UsuarioDesativado {
	
	@Id
	private String nrCpf;
	
	private String icTipo; // AFASTADO / EXONERADO / REATIVADO
	
	private Date dtAlterado;
	
	private String matriculas;
	
	public UsuarioDesativado() {
    }
	

	public UsuarioDesativado(String matriculas, String nrCpf, String icTipo, Date dtAlterado) {
		super();
		this.nrCpf = nrCpf;
		this.icTipo = icTipo;
		this.dtAlterado = dtAlterado;
		this.matriculas = cortarInicio(matriculas, 250);
	}
	
	private String cortarInicio(String texto, int limite) {
	    if (texto == null) {
	        return null;
	    }

	    if (texto.length() <= limite) {
	        return texto;
	    }

	    return "..." + texto.substring(limite);
	}

	public String getNrCpf() {
		return nrCpf;
	}

	public void setNrCpf(String nrCpf) {
		this.nrCpf = nrCpf;
	}

	public String getIcTipo() {
		return icTipo;
	}

	public void setIcTipo(String icTipo) {
		this.icTipo = icTipo;
	}

	public Date getDtAlterado() {
		return dtAlterado;
	}

	public void setDtAlterado(Date dtAlterado) {
		this.dtAlterado = dtAlterado;
	}

	public String getMatriculas() {
		return matriculas;
	}

	public void setMatriculas(String matriculas) {
		this.matriculas = matriculas;
	}
	
	
	
	
	
}
