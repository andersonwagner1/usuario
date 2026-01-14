package br.com.prefeitura.usuario.dto;

import java.util.List;

public class CadastroUsuarioPostDto {
	
	//private String Usuario;
	private String cdUsuario;
	private String deEmail;
	private String dtAtivacao;
	private String nmUsuario;
	
	

	private String nuCpfcnpj;
	private List<String> perfis;
	
	private String deInformacoes;

	//private List<PerfilDto> perfisAux;
	private List<CadastroSetorPostDto> setores;
	private CadastroSetorPostDto setor;
	
	
	

	//private List<ProcessoDto> processos;
	
	//private Date dtUltimoAcesso;
	//private Date dtDesativado;
	
	public List<CadastroSetorPostDto> getSetores() {
		return setores;
	}
	public void setSetores(List<CadastroSetorPostDto> setores) {
		this.setores = setores;
	}
	private String flAtivo;
	
	private String icSituacaoRh;
	private String dsProntuario;
	public String getCdUsuario() {
		return cdUsuario;
	}
	public void setCdUsuario(String cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	public String getDeEmail() {
		return deEmail;
	}
	public void setDeEmail(String deEmail) {
		this.deEmail = deEmail;
	}
	public String getDtAtivacao() {
		return dtAtivacao;
	}
	public void setDtAtivacao(String dtAtivacao) {
		this.dtAtivacao = dtAtivacao;
	}
	public String getNmUsuario() {
		return nmUsuario;
	}
	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}
	public String getNuCpfcnpj() {
		return nuCpfcnpj;
	}
	public void setNuCpfcnpj(String nuCpfcnpj) {
		this.nuCpfcnpj = nuCpfcnpj;
	}
	public List<String> getPerfis() {
		return perfis;
	}
	public void setPerfis(List<String> perfis) {
		this.perfis = perfis;
	}
	public String getDeInformacoes() {
		return deInformacoes;
	}
	public void setDeInformacoes(String deInformacoes) {
		this.deInformacoes = deInformacoes;
	}
	public String getFlAtivo() {
		return flAtivo;
	}
	public void setFlAtivo(String flAtivo) {
		this.flAtivo = flAtivo;
	}
	public String getIcSituacaoRh() {
		return icSituacaoRh;
	}
	public void setIcSituacaoRh(String icSituacaoRh) {
		this.icSituacaoRh = icSituacaoRh;
	}
	public String getDsProntuario() {
		return dsProntuario;
	}
	public void setDsProntuario(String dsProntuario) {
		this.dsProntuario = dsProntuario;
	}
	public CadastroSetorPostDto getSetor() {
		return setor;
	}
	public void setSetor(CadastroSetorPostDto setor) {
		this.setor = setor;
	}

	

	
	
	
/*
	public String getIcTipoChamado() {
		return icTipoChamado;
	}

	public void setIcTipoChamado(String icTipoChamado) {
		this.icTipoChamado = icTipoChamado;
	}

	public String getNrChamado() {
		return nrChamado;
	}

	public void setNrChamado(String nrChamado) {
		this.nrChamado = nrChamado;
	}*/

	
	

}
