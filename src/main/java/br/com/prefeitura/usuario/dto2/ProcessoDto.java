package br.com.prefeitura.usuario.dto2;

import br.com.prefeitura.usuario.dto.CadastroSetorPostDto;
import br.com.prefeitura.usuario.dto.CadastroUsuarioPostDto;



public class ProcessoDto {

	private String numeroProcessoFormatado;
	private String usuarioCadastro;
	private String situacao;
	private String controleAcesso;
	private String detalhamento;
	private String classificacao;

	private Long cdFila;
	private String nuprocFormatado;
	private CadastroSetorPostDto setorAtual;
	private CadastroSetorPostDto setorOrigem;
	private CadastroUsuarioPostDto usuarioAtual;
	private Integer nuProcesso;
	private Integer nuAno;
	private Integer nuProcessoOficial;



	public String getNumeroProcessoFormatado() {
		return numeroProcessoFormatado;
	}

	public void setNumeroProcessoFormatado(String numeroProcessoFormatado) {
		this.numeroProcessoFormatado = numeroProcessoFormatado;
	}

	public String getUsuarioCadastro() {
		return usuarioCadastro;
	}

	public void setUsuarioCadastro(String usuarioCadastro) {
		this.usuarioCadastro = usuarioCadastro;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getControleAcesso() {
		return controleAcesso;
	}

	public void setControleAcesso(String controleAcesso) {
		this.controleAcesso = controleAcesso;
	}

	public String getDetalhamento() {
		return detalhamento;
	}

	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}

	public Long getCdFila() {
		return cdFila;
	}

	public void setCdFila(Long cdFila) {
		this.cdFila = cdFila;
	}

	public String getNuprocFormatado() {
		return nuprocFormatado;
	}

	public void setNuprocFormatado(String nuprocFormatado) {
		this.nuprocFormatado = nuprocFormatado;
	}

	public CadastroSetorPostDto getSetorAtual() {
		return setorAtual;
	}

	public void setSetorAtual(CadastroSetorPostDto setorAtual) {
		this.setorAtual = setorAtual;
	}

	public CadastroUsuarioPostDto getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(CadastroUsuarioPostDto usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	public Integer getNuProcesso() {
		return nuProcesso;
	}

	public void setNuProcesso(Integer nuProcesso) {
		this.nuProcesso = nuProcesso;
	}

	public Integer getNuAno() {
		return nuAno;
	}

	public void setNuAno(Integer nuAno) {
		this.nuAno = nuAno;
	}

	public Integer getNuProcessoOficial() {
		return nuProcessoOficial;
	}

	public void setNuProcessoOficial(Integer nuProcessoOficial) {
		this.nuProcessoOficial = nuProcessoOficial;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public CadastroSetorPostDto getSetorOrigem() {
		return setorOrigem;
	}

	public void setSetorOrigem(CadastroSetorPostDto setorOrigem) {
		this.setorOrigem = setorOrigem;
	}
	

}
