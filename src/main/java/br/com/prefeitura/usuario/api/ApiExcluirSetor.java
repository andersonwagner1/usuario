package br.com.prefeitura.usuario.api;

import br.com.prefeitura.usuario.api.solar.ApiCallSolar;
import br.com.prefeitura.usuario.dto2.ExcluirSetorDTO;

import java.util.HashMap;

import java.util.Map;

public class ApiExcluirSetor {

    private final String apiUrl = "https://eprocesso-hmg.diadema.sp.gov.br:443/solarbpm-integracao/usuario/excluir-setor";

    public String excluir(ExcluirSetorDTO dto) {
        try {
            // Monta o Map no formato esperado pelo endpoint
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("setoresUsuarios", dto.getSetoresUsuarios());

            // Chama a API com Map + DELETE
            ApiCallSolar apiCall = new ApiCallSolar(apiUrl);
            return apiCall.executar(requestBody, "DELETE").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\":\"erro\",\"message\":\"" + e.getMessage() + "\"}";
        }
    }
}