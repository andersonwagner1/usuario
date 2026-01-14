package br.com.prefeitura.usuario;

import br.com.prefeitura.usuario.api.ApiEncaminhamentoProcesso;
import br.com.prefeitura.usuario.api.ApiEncaminhamentoProcessoInterpessoal;
import br.com.prefeitura.usuario.api.ApiEncaminhamentoProcessoUsuarioOrgao;
import br.com.prefeitura.usuario.api.ApiExcluirEncaminhamento;
import br.com.prefeitura.usuario.api.ApiReceberProcesso;

public class Encaminhamento {
	
public static void main(String args[]) {
	
	String retorno = "";
	
	
	
	ApiReceberProcesso apiREceber = new ApiReceberProcesso();
	//apiREceber.receberProcesso("SISTEMA", "PMDI 00019206/2025");
	 System.out.println("Retorno API: " + retorno);
	ApiEncaminhamentoProcessoUsuarioOrgao api = new ApiEncaminhamentoProcessoUsuarioOrgao();
	while(!retorno.equals("Erro")){
		retorno = api.encaminharProcessoParaUsuarioOrgao("JOSE.MOREIRA", "PMDI 0019206/2025 ", "Encaminhado pois o usuario 'USUARIO' esta inativo", "PMDI", "SO-2");
		System.out.println("Retorno API: " + retorno);
	}
	
	
	
	// "Encaminhamento devido a transferencia de setor do usuario"
	
	
		//ApiEncaminhamentoProcessoUsuarioOrgao usuario = new ApiEncaminhamentoProcessoUsuarioOrgao();
		//usuario.encaminharProcessoParaUsuarioOrgao(cdUsuarioDestino, nuProcessoFormatado, parecerEncaminhamento, sgOrgaoProcesso, sgSetorDestino);
	
	//ApiExcluirEncaminhamento encaminhamento = new ApiExcluirEncaminhamento();
//	retorno = encaminhamento.excluirEncaminhamento("8939d797-b9df-4700-b974-c1c118b78393");
	
	
	
	


	
		//realizar o procedimento de encaminhamento
        //ApiEncaminhamentoProcesso api = new ApiEncaminhamentoProcesso();
        //retorno = api.encaminharProcesso("PMDI 0005968/2024", "Encaminhado para o setor pois o usuario não pertece ao setor", "SF-22");
        
        
       /*ApiEncaminhamentoProcessoInterpessoal apiEncaminhadoInterpessoal = new ApiEncaminhamentoProcessoInterpessoal();
        
        retorno = apiEncaminhadoInterpessoal.encaminharProcessoParaUsuario(
                "MANOEL.TEIXEIRA",
                false,
                false,
                "2025-000456", 
                "Encaminhado para conferência",
                "SETOR_CONTABIL"
        );
        */
        System.out.println("Retorno API: " + retorno);
    }

}
