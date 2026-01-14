package br.com.prefeitura.usuario.main.exonerados2;

import java.sql.SQLException;
import java.util.List;

import br.com.prefeitura.usuario.dao.UsuarioDao;
import br.com.prefeitura.usuario.dto2.UsuarioExcluirAfastadoDto;

public class MainVerificaUsuarioProcessoEletronico {

    /**
     * MÉTODO PRINCIPAL - PONTO DE ENTRADA ÚNICO
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
    	System.out.println("--- INICIANDO JOB DE NOTIFICAÇÃO ---");
    	 
    	 
    	UsuarioDao dao = new UsuarioDao();
    	List<UsuarioExcluirAfastadoDto> listaUsuarioDestativados = dao.listarUsuarioExoneradosRh();
    	
    	System.out.println("Total: " + listaUsuarioDestativados.size());
    	
    	if(listaUsuarioDestativados.size() > 0){
    	
    		GravarBanco banco = new GravarBanco();
    		banco.salvarUsuariosDesativado(listaUsuarioDestativados);

    		EmailService enviar = new EmailService();
        	String corpo = new FormataCorpoEmail().formatarEmailProcessoEletronico(listaUsuarioDestativados, "usuario exonerados");
    		enviar.enviarRelatorioExoneradosEprocessos(corpo);
    	}   	
    	
    	List<UsuarioExcluirAfastadoDto> listaUsuarioAtivados =  dao.removerUsuariosDosExcluidos();
    	GravarBanco banco = new GravarBanco();
    	banco.reativarUsuario(listaUsuarioAtivados);
    	
    }
}
