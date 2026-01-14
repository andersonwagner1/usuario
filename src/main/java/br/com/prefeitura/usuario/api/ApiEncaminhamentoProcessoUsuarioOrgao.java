package br.com.prefeitura.usuario.api;

import java.util.HashMap;
import java.util.Map;

import br.com.prefeitura.usuario.Config;
import br.com.prefeitura.usuario.api.solar.ApiCallSolar;

public class ApiEncaminhamentoProcessoUsuarioOrgao extends ApiCallSolar {
    
    public ApiEncaminhamentoProcessoUsuarioOrgao() {
        super(Config.get("solar.url.encaminhar.processo.usuario.orgao"));
    }

    private Map<String, Object> criarParametrosEncaminhamento(
            String cdUsuarioDestino,
            boolean encaminhamentoForaDaFilaDeTrabalho,
            boolean gerarPecaEncaminhamento,
            String nuProcessoFormatado,
            String parecerEncaminhamento,
            String sgOrgaoProcesso,
            String sgSetorDestino) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("cdUsuarioDestino", cdUsuarioDestino);
        params.put("encaminhamentoForaDaFilaDeTrabalho", encaminhamentoForaDaFilaDeTrabalho);
        params.put("gerarPecaEncaminhamento", gerarPecaEncaminhamento);
        params.put("nuProcessoFormatado", nuProcessoFormatado);
        params.put("parecerEncaminhamento", parecerEncaminhamento);
        params.put("sgOrgaoProcesso", sgOrgaoProcesso);
        params.put("sgSetorDestino", sgSetorDestino);
        
        return params;
    }
    
    public String encaminharProcessoParaUsuarioOrgao(  String cdUsuarioDestino,
            String nuProcessoFormatado,
            String parecerEncaminhamento,
            String sgOrgaoProcesso,
            String sgSetorDestino){
            
            return encaminharProcessoParaUsuarioOrgao(cdUsuarioDestino, false, false, nuProcessoFormatado,parecerEncaminhamento, sgOrgaoProcesso, sgSetorDestino);
    }
    
    
    
    public String encaminharProcessoParaUsuarioOrgao(
            String cdUsuarioDestino,
            boolean encaminhamentoForaDaFilaDeTrabalho,
            boolean gerarPecaEncaminhamento,
            String nuProcessoFormatado,
            String parecerEncaminhamento,
            String sgOrgaoProcesso,
            String sgSetorDestino) {
        
        disable(); // desabilita logs extras, se houver
        
        Map<String, Object> parametros = criarParametrosEncaminhamento(
                cdUsuarioDestino,
                encaminhamentoForaDaFilaDeTrabalho,
                gerarPecaEncaminhamento,
                nuProcessoFormatado,
                parecerEncaminhamento,
                sgOrgaoProcesso,
                sgSetorDestino
        );
        
        StringBuilder response = executar(parametros, "POST");
        
        return response.toString(); 
    }
}
