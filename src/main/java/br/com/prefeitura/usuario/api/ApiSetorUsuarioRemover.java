package br.com.prefeitura.usuario.api;

import java.util.HashMap;
import java.util.Map;

import br.com.prefeitura.usuario.api.solar.ApiCallSolar;

public class ApiSetorUsuarioRemover extends ApiCallSolar {
    
    public ApiSetorUsuarioRemover(String chave) {
    	super("https://eprocesso.diadema.sp.gov.br:443/solarbpm-integracao/usuario/excluir-setor",chave);
    }
    
    private Map<String, Object> criarParametroVinculado(String cdUsuario , String[] setores) {    	
        Map<String, Object> params = new HashMap<>();
        params.put("cdUsuario", cdUsuario);       
        params.put("cdsPerfis", setores);
        return params;
    }
    
    
    public String adicionarUsuarioSetor(String cdUsuario, String[] setores) {
        disable();
        Map<String, Object> parametros = criarParametroVinculado(cdUsuario, setores);
        
        StringBuilder response = executar(parametros, "DELETE");
        return response.toString();
    }
}
