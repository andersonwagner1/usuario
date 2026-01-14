package br.com.prefeitura.usuario.api;

import java.util.HashMap;
import java.util.Map;

import br.com.prefeitura.usuario.api.solar.ApiCallSolar;
import br.com.prefeitura.usuario.dto2.EncaminharDTO;

public class ApiEncaminharProcesso {

    private final String apiUrl = "https://eprocesso.diadema.sp.gov.br:443/solarbpm-integracao/processo/encaminhar";

    public String encaminhar(EncaminharDTO dto) {
        try {
            // Monta o Map exatamente com os campos que a API espera
            Map<String, Object> jsonBody = new HashMap<>();
            jsonBody.put("anoProcesso", dto.getAnoProcesso());
            jsonBody.put("cdUsuarioDestino", dto.getCdUsuarioDestino());
            jsonBody.put("encaminhamentoForaDaFilaDeTrabalho", false);
            jsonBody.put("gerarPecaEncaminhamento", false);
            jsonBody.put("nuProcessoFormatado", dto.getNuProcessoFormatado());
            jsonBody.put("parecerEncaminhamento", dto.getParecerEncaminhamento());
            jsonBody.put("sgOrgaoProcesso", dto.getSgOrgaoProcesso());
            jsonBody.put("sgSetorDestino", dto.getSgSetorDestino());

            // Passa o Map diretamente para o ApiCallSolar, como os outros c�digos j� fazem
            ApiCallSolar apiCall = new ApiCallSolar(apiUrl);
            return apiCall.executar(jsonBody, "POST").toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "{\"status\":\"erro\",\"message\":\"" + e.getMessage() + "\"}";
        }
    }
}