package br.com.prefeitura.usuario.dto2;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProcessoResponse {
    private String unidadeResponsavel;
    private String unidadeOrigem;
    private String usuarioCadastro;
    private String dataHoraCadastro;
    private String classificacao;
    private String codigoCompletoClassificacao;
    private String situacao;
    private String controleAcesso;
    private String detalhamento;
    private List<Interessado> interessados;


    public String getUnidadeResponsavel() {
        return unidadeResponsavel;
    }

    public void setUnidadeResponsavel(String unidadeResponsavel) {
        this.unidadeResponsavel = unidadeResponsavel;
    }

    public String getUnidadeOrigem() {
        return unidadeOrigem;
    }

    public void setUnidadeOrigem(String unidadeOrigem) {
        this.unidadeOrigem = unidadeOrigem;
    }

    public String getUsuarioCadastro() {
        return usuarioCadastro;
    }

    public void setUsuarioCadastro(String usuarioCadastro) {
        this.usuarioCadastro = usuarioCadastro;
    }

    public String getDataHoraCadastro() {
        return dataHoraCadastro;
    }

    public void setDataHoraCadastro(String dataHoraCadastro) {
        this.dataHoraCadastro = dataHoraCadastro;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getCodigoCompletoClassificacao() {
        return codigoCompletoClassificacao;
    }

    public void setCodigoCompletoClassificacao(String codigoCompletoClassificacao) {
        this.codigoCompletoClassificacao = codigoCompletoClassificacao;
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

    public List<Interessado> getInteressados() {
        return interessados;
    }

    public void setInteressados(List<Interessado> interessados) {
        this.interessados = interessados;
    }

}
