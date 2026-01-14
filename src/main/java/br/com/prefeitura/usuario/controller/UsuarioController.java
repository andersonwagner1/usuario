package br.com.prefeitura.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prefeitura.usuario.api.ApiEncaminharProcesso;
import br.com.prefeitura.usuario.api.ApiExcluirSetor;
import br.com.prefeitura.usuario.api.ApiInsereSetor;
import br.com.prefeitura.usuario.api.ApiReceberProcesso;
import br.com.prefeitura.usuario.api.ApiCadastrarUsuario;
import br.com.prefeitura.usuario.dto.CadastroUsuarioPostDto;
import br.com.prefeitura.usuario.dto2.EncaminharDTO;
import br.com.prefeitura.usuario.dto2.ExcluirSetorDTO;
import br.com.prefeitura.usuario.dto2.ReceberProcessoDTO;

import br.com.prefeitura.usuario.dto2.RetornoDto;
import br.com.prefeitura.usuario.service.UsuarioService;

/**
 * Realizar consultar basica no sistema
 * @author anderson.oliveira
 *
 */
@RestController
@RequestMapping("/api/usuario")
//@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
	
	
	private UsuarioService usuarioService;
	//private ApiEncaminharProcesso apiEncaminharProcesso = new ApiEncaminharProcesso();



	@Autowired
	public UsuarioController(UsuarioService usuarioService) {	
		this.usuarioService = usuarioService;
	
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Bem vindo Util";
	}
	
	@GetMapping("/procurar-rh/{cpf}")
	public ResponseEntity<CadastroUsuarioPostDto> consultarCPfNoSistemaRh(@PathVariable("cpf") String cpf) {
		RetornoDto<CadastroUsuarioPostDto> retorno = usuarioService.consultarUsuarioRh(cpf);
		return new ResponseEntity<CadastroUsuarioPostDto>(retorno.getResultado(), HttpStatus.OK);		
	}
	
	@GetMapping("/pesquisar/{nome}")
	public ResponseEntity<RetornoDto<List<CadastroUsuarioPostDto>>> listarNomeNoSistemaRh(@PathVariable("nome") String nome) {
		if(nome != null){
			
		}
		
		nome = nome.toUpperCase();
		RetornoDto<List<CadastroUsuarioPostDto>> retorno = usuarioService.listarNomeNoSistemaRh(nome);
		return new ResponseEntity<RetornoDto<List<CadastroUsuarioPostDto>>>(retorno, HttpStatus.OK);		
	}
	
	@GetMapping("/consulta/{cdusuario}/dados")
	public ResponseEntity<CadastroUsuarioPostDto> consultaUsuario(@PathVariable("cdusuario") String cdUsuario) {
	//	RetornoDto<CadastroUsuarioPostDto> retorno = usuarioService.consultarUsuarioNoProcessoEletronico(cdUsuario);
		//return new ResponseEntity<CadastroUsuarioPostDto>(retorno.getResultado(), HttpStatus.OK);
		return null;
	}
	
	
	@GetMapping("/consulta/{cdusuario}/setores")
	public ResponseEntity<List<CadastroUsuarioPostDto>> consultaSetores(@PathVariable("cdusuario") String cdUsuario) {
		RetornoDto<List<CadastroUsuarioPostDto>> retorno = usuarioService.listarNomeNoSistemaRh(cdUsuario);
		return new ResponseEntity<List<CadastroUsuarioPostDto>>(retorno.getResultado(), HttpStatus.OK);		
	}
	
	
	
	
	
	
	
	/*@GetMapping("/listarTodosUsuariosAtivos")
	public ResponseEntity<List<Data<DaResultado>>> listarTodosUsuarioAtivos() {
		RetornoDto<List<UsuarioDto>> resultado;		
	//	resultado = usuarioService.listarUsuarioPorNomeCpfEmailOuLogin();
		return new ResponseEntity<List<UsuarioDto>>(resultado.getResultado(), HttpStatus.OK);	
			
	}*/
	
	
	/*@PostMapping("/pesquisar")
	public ResponseEntity<List<UsuarioDto>> pesquisarUsuarioCadastradoNoSistema(@RequestBody String usuario) {
		RetornoDto<List<UsuarioDto>> resultado;		
		resultado = usuarioService.listarUsuarioPorNomeCpfEmailOuLogin(usuario);
		return new ResponseEntity<List<UsuarioDto>>(resultado.getResultado(), HttpStatus.OK);		
	}*/
	
	
	
	
	
	
	
	
	
	/**
	 * Lista apenas os perfis que são adicionados
	 * @param login
	 * @return
	 */@Deprecated
	/*@GetMapping("/perfils/{login}")
	public ResponseEntity<List<PerfilDto>> listarPerfisPorUsuario(@PathVariable("login") String login) {
		RetornoDto<List<PerfilDto>> resultado;
		resultado = usuarioService.listarPerfisPorUsuarios(login);
		return new ResponseEntity<List<PerfilDto>>(resultado.getResultado(), HttpStatus.OK);		
	}
	 @Deprecated
	@GetMapping("/perfils/importantes/{login}")
	public ResponseEntity<List<PerfilDto>> listarPerfisImportantePorUsuario(@PathVariable("login") String login) {
		RetornoDto<List<PerfilDto>> resultado;
		resultado = usuarioService.listarApenasPerfisImportantesPorLogin(login);
		return new ResponseEntity<List<PerfilDto>>(resultado.getResultado(), HttpStatus.OK);		
	}*/
	
	
	
	
	


	
	
	
	
@PostMapping("/cadastrar")    
public ResponseEntity<RetornoDto<String>> cadastrarUsuario(@RequestBody CadastroUsuarioPostDto usuario) throws Exception {
    RetornoDto<String> retorn = new RetornoDto<String>();
    String ret = null;
    try {
    	ApiCadastrarUsuario usuarioApi = new ApiCadastrarUsuario();
    	ret = usuarioApi.cadastrarUsuario(usuario);
    	usuarioService.registrarUsuarioNoSistema(usuario);
        retorn.setResultado(ret);
        retorn.setSituacao(true);
        return new ResponseEntity<RetornoDto<String>>(retorn, HttpStatus.OK);

    } catch (Exception e) {
        retorn = new RetornoDto<String>();
        retorn.setDescricao(e.getMessage());
        retorn.setSituacao(false);
        retorn.setResultado(null);
        e.printStackTrace();
        throw new Exception(e.getMessage());
    }
}
	/* @Deprecated
	@PostMapping("/atendimento/usuario-cadastrar")	
	public ResponseEntity<RetornoDto<String>> atender(@RequestBody CadastroUsuarioPostDto atendimento){
		RetornoDto<String> retorn = new RetornoDto<String>();
		
		RetornoApiEprocessoDto ret = UsuarioService.realizarCadastroUsuarioNoProcessoEletronico(atendimento);
		
		retorn.setResultado(ret.getError() + " " + ret.getMessage() + " " + ret.getValidationMessage());
		
		
		return new ResponseEntity<RetornoDto<String>>(retorn, HttpStatus.OK);
	}*/
	 @Deprecated
	@PostMapping("/atendimento/Inserir/Setor")
	public ResponseEntity<String> InserirSetor(@RequestBody CadastroUsuarioPostDto usuario) {
	    if (usuario == null || usuario.getCdUsuario() == null || usuario.getCdUsuario().isEmpty()) {
	        return ResponseEntity.badRequest()
	                .body("{\"status\":\"erro\",\"message\":\"cdUsuario n�o pode ser nulo ou vazio\"}");
	    }

	    try {
	        ApiInsereSetor api = new ApiInsereSetor();
	        String resposta = api.adicionarSetor(usuario);
	        return ResponseEntity.ok(resposta);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500)
	                .body("{\"status\":\"erro\",\"message\":\"Erro interno ao chamar API Solar\"}");
	    }
	}

	
	/* @Deprecated
	@PostMapping("/Pesquisar/Setor")
    public ResponseEntity<String> pesquisarSetor(@RequestBody PesquisarSetorDto pesquisaDto) {
        ApiSetor api = new ApiSetor();
        String resposta = api.pesquisarSetor(pesquisaDto.getPesquisa());
        return ResponseEntity.ok(resposta);
    }
	*/
	
	
	 @Deprecated
	@PostMapping("/encaminhar")
	public ResponseEntity<String> encaminhar(@RequestBody EncaminharDTO dto) {
	    try {
	        ApiEncaminharProcesso api = new ApiEncaminharProcesso();
	        String resposta = api.encaminhar(dto);
	        return ResponseEntity.ok(resposta);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500)
	                .body("{\"status\":\"erro\",\"message\":\"Erro interno ao chamar API Solar\"}");
	    }
	}
	
	
	 @Deprecated
	@PostMapping("/receber")
	public ResponseEntity<String> receber(@RequestBody ReceberProcessoDTO dto) {
	    try {
	        ApiReceberProcesso api = new ApiReceberProcesso();
	        String response = api.gerar(dto);
	        return ResponseEntity.ok(response);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("{\"status\":\"erro\",\"message\":\"" + e.getMessage() + "\"}");
	    }
	}
	
	
	
	
	
	
	 @Deprecated
	  @DeleteMapping("/excluir-setor")
	    public ResponseEntity<String> excluirSetor(@RequestBody ExcluirSetorDTO dto) {
	        try {
	            ApiExcluirSetor api = new ApiExcluirSetor();
	            String response = api.excluir(dto);
	            return ResponseEntity.ok(response);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body("{\"status\":\"erro\",\"message\":\"" + e.getMessage() + "\"}");
	        }
	    }
	}
	
	
	











