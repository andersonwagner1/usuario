package br.com.prefeitura.usuario.api;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import br.com.prefeitura.usuario.api.solar.ApiCallSolar;

public class ApiUsuarioBloqueio extends ApiCallSolar {
    
    public ApiUsuarioBloqueio(String chave) {
    	//super("https://eprocesso.diadema.sp.gov.br:443/solarbpm-integracao/usuario/alterar-usuario",chave);
    	super("https://eprocesso.diadema.sp.gov.br:443/solarbpm-integracao/usuario/alterar-usuario");
    }
    
    
    
   
    
    private String dataHojeMaisUmDia(){
    	  // Obter a data de hoje
        LocalDate hoje = LocalDate.now();

        // Adicionar um dia à data de hoje
        LocalDate amanha = hoje.plusDays(1);

        // Formatar a data resultante no formato "YYYY-MM-DD"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dataFormatada = amanha.format(formatter);

        // Imprimir a data formatada
        System.out.println("Data de hoje + 1 dia: " + dataFormatada);
        return dataFormatada;
    }
    
    private Map<String, Object> criarParametrosBloqueio(String cdUsuario , String informacao) {    	
        Map<String, Object> params = new HashMap<>();
        params.put("cdUsuario", cdUsuario);       
        params.put("dtFim",dataHojeMaisUmDia());
        params.put("flHabilitado", "N");
        params.put("infos", informacao);
        
        return params;
    }
    
    private Map<String, Object> criarParametrosDesbloqueio(String cdUsuario , String informacao) {    	
        Map<String, Object> params = new HashMap<>();
        params.put("cdUsuario", cdUsuario);       
        params.put("dtFim","");
        params.put("flHabilitado", "S");
        params.put("infos", informacao);
        
        return params;
    }
    
    
    public String desbloquearUsuario(String cdUsuario, String informacao) {
        disable();
        Map<String, Object> parametros = criarParametrosDesbloqueio(cdUsuario, informacao);
        
        StringBuilder response = executar(parametros, "PUT");
        return response.toString();
    }
    
    
    public String bloquearUsuario(String cdUsuario, String informacao) {
        disable();
        Map<String, Object> parametros = criarParametrosBloqueio(cdUsuario, informacao);
        
        StringBuilder response = executar(parametros, "PUT");
        return response.toString();
    }
    
    
    
    
	public static void main(String args[]) {
		

		ApiUsuarioBloqueio apiUsuario = new ApiUsuarioBloqueio("");
		
		//ConverterDtoJson.mostarJson(usuarioDto);
		
		// Sete outras informações do usuário conforme necessário
		apiUsuario.bloquearUsuario("LOURDES.ARAUJO","121956");
		apiUsuario.bloquearUsuario("LOURIVAL.SILVA","121956");
		//apiUsuario.desbloquearUsuario("MARCIA.PAULO", "123456");
	}
}
