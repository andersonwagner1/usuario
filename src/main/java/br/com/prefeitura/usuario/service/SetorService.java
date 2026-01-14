package br.com.prefeitura.usuario.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.prefeitura.usuario.api.ApiSetor;
import br.com.prefeitura.usuario.dto.CadastroSetorPostDto;
import br.com.prefeitura.usuario.dto2.RetornoDto;
import br.com.prefeitura.usuario.util.JsonConverter;


@Service
public class SetorService {
	



	
	
	
	public RetornoDto<List<CadastroSetorPostDto>> listarSetoresPorNomeOuSigla(String descricao){
		ApiSetor setorService = new ApiSetor();
		RetornoDto<List<CadastroSetorPostDto>>  ret = new RetornoDto<List<CadastroSetorPostDto>>();
		List<CadastroSetorPostDto> listaUsuario;
		//try {
			String retorno =  setorService.pesquisarSetor(descricao);
			System.out.println(retorno);
			listaUsuario = JsonConverter.converterJsonParaLista(retorno, CadastroSetorPostDto.class);
			
			ret.setResultado(listaUsuario);		
			return ret;
		/*} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret.setSituacao(false);
			ret.setDescricao(e.getMessage());
			return ret;
		}*/
	}
	
	
}
