package br.com.prefeitura.usuario.dto2;

public class EncaminharDTO {
	
	  private int anoProcesso;
	    private String cdUsuarioDestino;
	    private boolean encaminhamentoForaDaFilaDeTrabalho = false; // fixo
	    private boolean gerarPecaEncaminhamento = false; // fixo
	    private String nuProcessoFormatado;
	    private String parecerEncaminhamento;
	    private String sgOrgaoProcesso;
	    private String sgSetorDestino;

	    // Getters e Setters
	    public int getAnoProcesso() {
	        return anoProcesso;
	    }
	    public void setAnoProcesso(int anoProcesso) {
	        this.anoProcesso = anoProcesso;
	    }
	    public String getCdUsuarioDestino() {
	        return cdUsuarioDestino;
	    }
	    public void setCdUsuarioDestino(String cdUsuarioDestino) {
	        this.cdUsuarioDestino = cdUsuarioDestino;
	    }
	    public boolean isEncaminhamentoForaDaFilaDeTrabalho() {
	        return encaminhamentoForaDaFilaDeTrabalho;
	    }
	    public boolean isGerarPecaEncaminhamento() {
	        return gerarPecaEncaminhamento;
	    }
	    public String getNuProcessoFormatado() {
	        return nuProcessoFormatado;
	    }
	    public void setNuProcessoFormatado(String nuProcessoFormatado) {
	        this.nuProcessoFormatado = nuProcessoFormatado;
	    }
	    public String getParecerEncaminhamento() {
	        return parecerEncaminhamento;
	    }
	    public void setParecerEncaminhamento(String parecerEncaminhamento) {
	        this.parecerEncaminhamento = parecerEncaminhamento;
	    }
	    public String getSgOrgaoProcesso() {
	        return sgOrgaoProcesso;
	    }
	    public void setSgOrgaoProcesso(String sgOrgaoProcesso) {
	        this.sgOrgaoProcesso = sgOrgaoProcesso;
	    }
	    public String getSgSetorDestino() {
	        return sgSetorDestino;
	    }
	    public void setSgSetorDestino(String sgSetorDestino) {
	        this.sgSetorDestino = sgSetorDestino;
	    }
	}