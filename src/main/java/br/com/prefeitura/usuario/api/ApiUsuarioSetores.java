package br.com.prefeitura.usuario.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.prefeitura.usuario.api.solar.ApiCallSolar;
import br.com.prefeitura.usuario.dto.ConsultaUsuarioDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ApiUsuarioSetores {

    private final String apiUrl = "https://eprocesso.diadema.sp.gov.br:443/solarbpm-integracao/usuario/consulta";

    /**
     * @param usuario Ex: "ANDERSON.OLIVEIRA"
     * @param campoRetorno Ex: "UNIDADES"
     */
    private ObjectMapper mapper = null;
    public List<ConsultaUsuarioDTO> pesquisarSetor(String usuario, String campoRetorno) {
    	this.mapper = new ObjectMapper();
        try {
            // Monta o JSON Body usando Arrays.asList (compatível com Java 8)
            Map<String, Object> jsonBody = new HashMap<>();
            jsonBody.put("camposRetornos", Arrays.asList(campoRetorno));
            jsonBody.put("cdsUsuarios", Arrays.asList(usuario));

            // Instancia e executa a chamada
            ApiCallSolar apiCall = new ApiCallSolar(apiUrl);
            String jsonResposta = apiCall.executar(jsonBody, "POST").toString();

            // Deserialização para Lista de DTOs
            return mapper.readValue(jsonResposta, new TypeReference<List<ConsultaUsuarioDTO>>() {});

        } catch (Exception e) {
            e.printStackTrace();
            // Em Java 8, você pode retornar uma lista vazia para evitar NullPointerException no chamador
            return java.util.Collections.emptyList();
        }
    }
    
    public static void main (String arg[]){
    	ApiUsuarioSetores a = new ApiUsuarioSetores();
    	List<ConsultaUsuarioDTO> t = a.pesquisarSetor("ANDERSON.OLIVEIRA", "UNIDADES");
    	System.out.println("t");
    	
    }

   
    
}


