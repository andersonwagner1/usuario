package br.com.prefeitura.usuario.main.exonerados2;

import java.sql.SQLException;
import java.util.List;

import br.com.prefeitura.usuario.dao.UsuarioDao;
import br.com.prefeitura.usuario.dto2.UsuarioExcluirAfastadoDto;

public class MainVerificaUsuarioPrefeitura {

    /**
     * MÉTODO PRINCIPAL - PONTO DE ENTRADA ÚNICO
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
    	
    	UsuarioDao dao = new UsuarioDao();
    	
    	List<UsuarioExcluirAfastadoDto> listaUsuarioAfastadoNoRh = dao.listarUsuariosAfastadosNoRH();
    	
    	System.out.println("Total: " + listaUsuarioAfastadoNoRh.size());
    	
    	
    	
    	if(listaUsuarioAfastadoNoRh.size() > 0){
    		GravarBanco servicoBancoDados = new GravarBanco();
    		servicoBancoDados.salvarRegistroUsuarioAfastadoRh(listaUsuarioAfastadoNoRh);

    		EmailService servicoEmail = new EmailService();
        	String corpoEmail = new FormataCorpoEmail().formatarEmail(listaUsuarioAfastadoNoRh, "usuario afastado");
    		servicoEmail.enviarRelatorioAfastados(corpoEmail);
    	}   	
    	
    	
    	List<UsuarioExcluirAfastadoDto> listaUsuariosExoenradosNorRh = dao.listarUsuarioExoneradosRh();
    	
    	System.out.println("Total: " + listaUsuariosExoenradosNorRh.size());
    	
    	if(listaUsuariosExoenradosNorRh.size() > 0){    	
    		GravarBanco servicoBancoDados = new GravarBanco();
    		servicoBancoDados.salvarUsuariosDesativado(listaUsuariosExoenradosNorRh);

    		EmailService servicoEmail = new EmailService();
        	String corpo = new FormataCorpoEmail().formatarEmail(listaUsuariosExoenradosNorRh, "Usuário exonerados");
    		servicoEmail.enviarRelatorioExonerados(corpo);
    		servicoEmail.enviarRelatorioExoneradosEprocessos(corpo);
    	}   	
    	
    	List<UsuarioExcluirAfastadoDto> listaUsuarioAtivados =  dao.removerUsuariosDosExcluidos();
    	GravarBanco banco = new GravarBanco();
    	banco.reativarUsuario(listaUsuarioAtivados);
    	
    }
}
