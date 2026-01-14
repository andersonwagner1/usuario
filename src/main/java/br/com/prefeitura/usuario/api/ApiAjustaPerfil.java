package br.com.prefeitura.usuario.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.prefeitura.usuario.api.solar.ApiCallSolar;
import br.com.prefeitura.usuario.dto2.ProcessoResponse;
import br.com.prefeitura.usuario.dto2.ResultadoDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ApiAjustaPerfil extends ApiCallSolar {
    
    public static void main(String args[]) {
        ApiAjustaPerfil ajusta = new ApiAjustaPerfil();
        ajusta.deletarSetor("TESTE");
    }

    public ApiAjustaPerfil() {
        super("https://eprocesso.diadema.sp.gov.br:443/solarbpm-integracao/usuario/excluir-setor");
    }

    private Map<String, Object> criarParametrosDelete(String cdUsuario) {
        // Criar o mapa de parâmetros
        Map<String, Object> params = new HashMap<>();
        
        // Criar a lista de setoresUsuarios
        List<Map<String, Object>> setoresUsuarios = new ArrayList<>();
        
        // Criar o mapa de setoresUsuario com o cdUsuario
        Map<String, Object> setorUsuario = new HashMap<>();
        setorUsuario.put("cdUsuario", cdUsuario);
        
   
        
 List<Map<String, String>> unidades = new ArrayList<>();
        
        Map<String, String> unidade1 = new HashMap<>();
        unidade1.put("sgOrgao", "PMD");
        unidade1.put("sgUnidade", "SE-570");
        unidades.add(unidade1);
        
       Map<String, String> unidade2 = new HashMap<>();
        unidade2.put("sgOrgao", "PMD");
        unidade2.put("sgUnidade", "SE-566");
        unidades.add(unidade2);
        
        
        
      
        
        // Adicionar a lista de unidades ao setorUsuario
        setorUsuario.put("unidades", unidades);
        
        // Adicionar o setorUsuario à lista de setoresUsuarios
        setoresUsuarios.add(setorUsuario);
        
        // Adicionar a lista de setoresUsuarios ao mapa de parâmetros
        params.put("setoresUsuarios", setoresUsuarios);
        
        // Retornar os parâmetros
        return params;
    }

    public ResultadoDto<ResultadoDto> deletarSetor(String cdUsuario) {
    	
    	
    	
        Map<String, Object> responseMap = criarParametrosDelete(cdUsuario);
        
        
        
        StringBuilder response = executar(responseMap, "DELETE");


        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convertendo a resposta para JSON
            String jsonResponse = objectMapper.writeValueAsString(responseMap);
            
            // Criando resposta DTO
            ProcessoResponse respostaAPIDTO = objectMapper.readValue(jsonResponse, ProcessoResponse.class);
            ResultadoDto<ProcessoResponse> resultado = new ResultadoDto<>();
            resultado.setObjeto(respostaAPIDTO);
            resultado.setResultado("Sucesso");
            resultado.setSucesso(true);
            return null;
        } catch (JsonProcessingException e) {
            ResultadoDto<ProcessoResponse> resultado = new ResultadoDto<>();
            resultado.setObjeto(null);
            resultado.setResultado(e.getMessage());
            resultado.setSucesso(false);
            e.printStackTrace();
            return null;
        }
    }
}
