package br.com.prefeitura.usuario.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prefeitura.usuario.dto.CadastroSetorPostDto;
import br.com.prefeitura.usuario.dto2.RetornoDto;
import br.com.prefeitura.usuario.service.SetorService;


/**
 * Realizar consultar basica no sistema
 * 
 * @author anderson.oliveira
 *
 */

@RestController
@RequestMapping("/api/setor")
public class SetorController {
	
	
	private SetorService setorService = new SetorService();




	/*@Autowired
	public SetorController(SetorService setorService) {	
		this.setorService = setorService;
	
	}*/

	@Autowired
	public SetorController() {
	}

	@GetMapping("/hello")
	public String sayHello() {
		return "Bem vindo Util";
	}
	
	@GetMapping("/pesquisar/setor/{setor}")
	public ResponseEntity<RetornoDto<List<CadastroSetorPostDto>>> listarSetoresPorNomeOuSigla(@PathVariable("setor") String setor) {
		RetornoDto<List<CadastroSetorPostDto>> resultado;
		resultado = setorService.listarSetoresPorNomeOuSigla(setor);
		return new ResponseEntity<RetornoDto<List<CadastroSetorPostDto>>>(resultado, HttpStatus.OK);
		
	}


	/*@PostMapping("/pesquisar")
	public ResponseEntity<RetornoDto<SetorDto>> pesquisarSetor(@RequestBody PesquisarSetorDto pesquisaDto) {
		ApiPesquisarSetor api = new ApiPesquisarSetor();
		resposta = api.pesquisarSetor(pesquisaDto.getPesquisa());
		return ResponseEntity.ok(resposta);
	}*/
}
