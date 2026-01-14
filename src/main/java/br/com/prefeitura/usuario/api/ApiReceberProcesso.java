package br.com.prefeitura.usuario.api;


import br.com.prefeitura.usuario.api.solar.ApiCallSolar;
import br.com.prefeitura.usuario.dto2.ReceberProcessoDTO;

import java.util.HashMap;
import java.util.Map;

public class ApiReceberProcesso {

    // coloque aqui a URL correta desse endpoint
    private static final String apiUrl = "https://eprocesso.diadema.sp.gov.br:443/solarbpm-integracao/processo/receber";

    public String gerar(ReceberProcessoDTO dto) {
        try {
            // Instancia ApiCallSolar passando a URL
            ApiCallSolar apiCall = new ApiCallSolar(apiUrl);

            // Monta o body da requisi��o
            Map<String, Object> body = new HashMap<>();
            body.put("cdUsuario", dto.getCdUsuario());
            body.put("nuProcessoFormatado", dto.getNuProcessoFormatado());

            // Executa e converte o resultado para String
            return apiCall.executar(body, "POST").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\":\"erro\",\"message\":\"" + e.getMessage() + "\"}";
        }
    }
}