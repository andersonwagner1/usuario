package br.com.prefeitura.usuario.dto;

import java.util.List;

public class ConsultaUsuarioDTO {
    private String cdUsuario;
    private String nmUsuario;
    private ConsultaUnidadeDTO unidadePadrao;
    private List<ConsultaUnidadeDTO> unidades;
    private Boolean flHabilitado;
    private String dtAtivacao;
    private String dtFim;

    // Getters e Setters
    public String getCdUsuario() { return cdUsuario; }
    public void setCdUsuario(String cdUsuario) { this.cdUsuario = cdUsuario; }

    public String getNmUsuario() { return nmUsuario; }
    public void setNmUsuario(String nmUsuario) { this.nmUsuario = nmUsuario; }

    public ConsultaUnidadeDTO getUnidadePadrao() { return unidadePadrao; }
    public void setUnidadePadrao(ConsultaUnidadeDTO unidadePadrao) { this.unidadePadrao = unidadePadrao; }

    public List<ConsultaUnidadeDTO> getUnidades() { return unidades; }
    public void setUnidades(List<ConsultaUnidadeDTO> unidades) { this.unidades = unidades; }

    public Boolean getFlHabilitado() { return flHabilitado; }
    public void setFlHabilitado(Boolean flHabilitado) { this.flHabilitado = flHabilitado; }

    public String getDtAtivacao() { return dtAtivacao; }
    public void setDtAtivacao(String dtAtivacao) { this.dtAtivacao = dtAtivacao; }

    public String getDtFim() { return dtFim; }
    public void setDtFim(String dtFim) { this.dtFim = dtFim; }
}