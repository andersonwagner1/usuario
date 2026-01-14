package br.com.prefeitura.usuario.api;

import java.util.HashMap;
import java.util.Map;

import br.com.prefeitura.usuario.api.solar.ApiCallSolar;



public class ApiSetor {

    private final String apiUrl = "https://eprocesso.diadema.sp.gov.br:443/solarbpm-integracao/orgao-setor/setores";

    public String pesquisarSetor(String pesquisa) {
        try {
            // Define os campos conforme a presen�a de espa�o
            String nmOrgaoSetor = "";
            String sgOrgaoSetor = "";
            
            if (pesquisa.trim().contains(" ")) {
                nmOrgaoSetor = pesquisa;   // nome completo
            } else {
                sgOrgaoSetor = pesquisa;   // c�digo curto
            }

            // Monta o corpo da requisi��o
            Map<String, Object> jsonBody = new HashMap<>();
            jsonBody.put("nmOrgaoSetor", nmOrgaoSetor);
            jsonBody.put("sgOrgaoSetor", sgOrgaoSetor);

            // Cria inst�ncia de ApiCallSolar
            ApiCallSolar apiCall = new ApiCallSolar(apiUrl);

            // Chama a API e converte StringBuilder para String
            return apiCall.executar(jsonBody, "POST").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"erro\":\"" + e.getMessage() + "\"}";
        }
    }
}