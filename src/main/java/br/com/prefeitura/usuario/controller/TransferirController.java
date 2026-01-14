package br.com.prefeitura.usuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prefeitura.usuario.api.ApiEncaminhamentoProcesso;
import br.com.prefeitura.usuario.dto2.EncaminharDTO;
import br.com.prefeitura.usuario.service.UsuarioService;

/**
 * Realizar consultar basica no sistema
 * 
 * @author anderson.oliveira
 *
 */
@RestController
@RequestMapping("/api/transferir")
public class TransferirController {

	//private UsuarioService usuarioService;

	@Autowired
	public TransferirController(UsuarioService usuarioService) {
	//	this.usuarioService = usuarioService;

	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Bem vindo Util";
	}
	
	
	@PostMapping("/encaminhar")
	public ResponseEntity<String> encaminhar(@RequestBody EncaminharDTO dto) {
	    try {
	        ApiEncaminhamentoProcesso api = new ApiEncaminhamentoProcesso();
	        String resposta = api.encaminharProcesso(dto);
	        return ResponseEntity.ok(resposta);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500)
	                .body("{\"status\":\"erro\",\"message\":\"Erro interno ao chamar API Solar\"}");
	    }
	}
	
}
