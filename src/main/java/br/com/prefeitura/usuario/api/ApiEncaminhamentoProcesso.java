package br.com.prefeitura.usuario.api;

import java.util.HashMap;
import java.util.Map;

import br.com.prefeitura.usuario.Config;
import br.com.prefeitura.usuario.api.solar.ApiCallSolar;
import br.com.prefeitura.usuario.dto2.EncaminharDTO;

public class ApiEncaminhamentoProcesso extends ApiCallSolar {
    
    public ApiEncaminhamentoProcesso() {
        super(Config.get("solar.url.encaminhar.processo"));
    }

    private Map<String, Object> criarParametrosEncaminhamento(
            boolean encaminhamentoForaDaFilaDeTrabalho,
            boolean gerarPecaEncaminhamento,
            String nuProcessoFormatado,
            String parecerEncaminhamento,
            String sgSetorDestino) {
        
        Map<String, Object> params = new HashMap<>();
        params.put("encaminhamentoForaDaFilaDeTrabalho", encaminhamentoForaDaFilaDeTrabalho);
        params.put("gerarPecaEncaminhamento", gerarPecaEncaminhamento);
        params.put("nuProcessoFormatado", nuProcessoFormatado);
        params.put("parecerEncaminhamento", parecerEncaminhamento);
        params.put("sgSetorDestino", sgSetorDestino);
        
        return params;
    }
    
    public String encaminharProcesso(EncaminharDTO dto) {
    	return encaminharProcesso(false, false, dto.getNuProcessoFormatado(), dto.getParecerEncaminhamento(), dto.getSgSetorDestino());
	}
    
    
    public String encaminharProcesso(
            String nuProcessoFormatado,
            String parecerEncaminhamento,
            String sgSetorDestino){        
          return encaminharProcesso(false, false, nuProcessoFormatado, parecerEncaminhamento,sgSetorDestino);
    }
        
    public String encaminharProcesso(
            boolean encaminhamentoForaDaFilaDeTrabalho,
            boolean gerarPecaEncaminhamento,
            String nuProcessoFormatado,
            String parecerEncaminhamento,
            String sgSetorDestino) {
        
        disable(); // desabilita logs extras, se for o caso
        
        Map<String, Object> parametros = criarParametrosEncaminhamento(
                encaminhamentoForaDaFilaDeTrabalho,
                gerarPecaEncaminhamento,
                nuProcessoFormatado,
                parecerEncaminhamento,
                sgSetorDestino
        );
        
        StringBuilder response = executar(parametros, "POST"); // Encaminhamento é envio, não alteração
        
        return response.toString(); // aqui deve vir algo como {"mensagem":"Processo encaminhado com sucesso","statusCode":200}
    }

	

    
}
