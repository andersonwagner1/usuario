package br.com.prefeitura.usuario.api;

import java.util.HashMap;
import java.util.Map;

import br.com.prefeitura.usuario.Config;
import br.com.prefeitura.usuario.api.solar.ApiCallSolar;

public class ApiExcluirEncaminhamento extends ApiCallSolar {
    
    public ApiExcluirEncaminhamento() {
        super(Config.get("solar.url.excluir.encaminhamento"));
    }

    private Map<String, Object> criarParametrosExclusao(String cdProcesso) {
        Map<String, Object> params = new HashMap<>();
        params.put("cdProcesso", cdProcesso);
        return params;
    }
    
    public String excluirEncaminhamento(String cdProcesso) {
        disable(); // desabilita logs extras (se configurado)
        
        Map<String, Object> parametros = criarParametrosExclusao(cdProcesso);
        
        // como é exclusão, usamos DELETE
        StringBuilder response = executar(parametros, "POST");
        
        return response.toString();
    }

    public static void main(String args[]) {
        
        ApiExcluirEncaminhamento api = new ApiExcluirEncaminhamento();
        
        String retorno = api.excluirEncaminhamento("PROC-2025-000888");
        
        System.out.println("Retorno API: " + retorno);
    }
}
