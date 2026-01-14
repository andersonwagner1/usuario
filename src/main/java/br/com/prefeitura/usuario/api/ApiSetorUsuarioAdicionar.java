package br.com.prefeitura.usuario.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.prefeitura.usuario.api.solar.ApiCallSolar;
import br.com.prefeitura.usuario.dto.CadastroUsuarioPostDto;

public class ApiSetorUsuarioAdicionar extends ApiCallSolar {

    public ApiSetorUsuarioAdicionar() {
        super("https://eprocesso-hmg.diadema.sp.gov.br:443/solarbpm-integracao/usuario/inserir-perfil");
    }
    
    public String adicionarUsuarioSetor(CadastroUsuarioPostDto usuario) {
        disable();
        
        if (usuario == null || usuario.getCdUsuario() == null) {
            throw new IllegalArgumentException("Usuário e cdUsuario não podem ser nulos.");
        }
        
        // Monta o mapa com os dados do usuário
        Map<String, Object> usuarioData = new HashMap<>();
        usuarioData.put("cdUsuario", usuario.getCdUsuario());
        
        // Define a lista de perfis com o valor fixo desejado
        List<String> cdsPerfis = new ArrayList<>();
        cdsPerfis.add("CPA_VISU_PROC_TODOS");
        usuarioData.put("cdsPerfis", cdsPerfis);
        
        List<String> usuarioPerfis = new ArrayList<>();
		// Cria a lista contendo o mapa do usuário
        //List<String> usuarioPerfis = new ArrayList<>();
        //usuarioPerfis .add(usuarioData);
        
        // Cria o mapa principal com a chave "usuarioPerfis"
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioPerfis", usuarioPerfis);
        
        // Executa a requisição POST e retorna a resposta como String
        StringBuilder response = executar(parametros, "POST");
        return response.toString();
    }
    
}