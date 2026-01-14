package br.com.prefeitura.usuario.api;

import java.util.HashMap;
import java.util.Map;

import br.com.prefeitura.usuario.Config;
import br.com.prefeitura.usuario.api.solar.ApiCallSolar;





public class ApiEncaminhamentoProcessoInterpessoal extends ApiCallSolar {
    
    public ApiEncaminhamentoProcessoInterpessoal(String chave) {
        super(Config.get("solar.url.encaminhar.processo.interperssoal"));
    }

    private Map<String, Object> criarParametrosEncaminhamento(
            String cdUsuarioDestino,
            boolean encaminhamentoForaDaFilaDeTrabalho,
            boolean gerarPecaEncaminhamento,
            String nuProcessoFormatado,
            String parecerEncaminhamento,
            String sgSetorDestino) {
        
                        
        Map<String, Object> params = new HashMap<>();
        params.put("cdUsuarioDestino", cdUsuarioDestino);
        params.put("encaminhamentoForaDaFilaDeTrabalho", encaminhamentoForaDaFilaDeTrabalho);
        params.put("gerarPecaEncaminhamento", gerarPecaEncaminhamento);
        params.put("nuProcessoFormatado", nuProcessoFormatado);
        params.put("parecerEncaminhamento", parecerEncaminhamento);
        params.put("sgSetorDestino", sgSetorDestino);
        
        return params;
    }
    
    public String encaminharProcessoParaUsuario(
            String cdUsuarioDestino,
            boolean encaminhamentoForaDaFilaDeTrabalho,
            boolean gerarPecaEncaminhamento,
            String nuProcessoFormatado,
            String parecerEncaminhamento,
            String sgSetorDestino) {
        
        disable(); // desabilita logs extras, se for o caso
        
        Map<String, Object> parametros = criarParametrosEncaminhamento(
                cdUsuarioDestino,
                encaminhamentoForaDaFilaDeTrabalho,
                gerarPecaEncaminhamento,
                nuProcessoFormatado,
                parecerEncaminhamento,
                sgSetorDestino
        );
        
        StringBuilder response = executar(parametros, "POST");
        
        return response.toString();
    }

   
}
