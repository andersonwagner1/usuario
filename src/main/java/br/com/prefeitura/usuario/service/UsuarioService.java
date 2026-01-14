package br.com.prefeitura.usuario.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import br.com.prefeitura.usuario.api.ApiUsuarioSetores;
import br.com.prefeitura.usuario.dao.UsuarioDao;
import br.com.prefeitura.usuario.dto.CadastroUsuarioPostDto;
import br.com.prefeitura.usuario.dto.ConsultaUsuarioDTO;
import br.com.prefeitura.usuario.dto2.PerfilDto;
import br.com.prefeitura.usuario.dto2.RetornoApiEprocessoDto;
import br.com.prefeitura.usuario.dto2.RetornoDto;
import br.com.prefeitura.usuario.model.UsuarioCadastrado;
import br.com.prefeitura.usuario.repository.UsuarioCadastradoRepository;


@Service
public class UsuarioService {
	
	
	
	private UsuarioDao usuarioDao = new UsuarioDao();
	private UsuarioCadastradoRepository usuarioCadastradoRepository;

	@Autowired
	public UsuarioService(UsuarioCadastradoRepository usuarioCadastradoRepository){
		this.usuarioCadastradoRepository = usuarioCadastradoRepository;
		
	}
	

	
	/**
	 * Localizar o processo
	 * @param atendimento
	 */
	//public void registrarLocalizarProcessoAtendimento(AtendimentoDto atendimento) {
	/*	RetornoDto<String>  ret = new RetornoDto<String>();
		
		String r = "";
		try {			
			LogInfo info = new LogInfo(atendimento);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			ret.setSituacao(false);
			ret.setDescricao(e.getMessage());
			
		}
		return ret;*/
		
		
		
		
//	}
	
	public RetornoDto<List<CadastroUsuarioPostDto>> listarNomeNoSistemaRh(String nome) {
		RetornoDto<List<CadastroUsuarioPostDto>>  ret = new RetornoDto<List<CadastroUsuarioPostDto>>();
		List<CadastroUsuarioPostDto> usuariosCadatrados;
		try {
			usuariosCadatrados = this.usuarioDao.listarUsuarioNoRhPorNome(nome);
			
			for(CadastroUsuarioPostDto usuario : usuariosCadatrados){
				 Optional<UsuarioCadastrado> usuariosCadatradoProcesso = usuarioCadastradoRepository.findById(usuario.getNuCpfcnpj());
				if(usuariosCadatradoProcesso.isPresent()){
					//UsuarioCadastrado u = usuariosCadatradoProcesso.get();
					usuario.setCdUsuario(usuariosCadatradoProcesso.get().getCdUsuario());
				}
				
			}
			
			
			
			ret.setResultado(usuariosCadatrados);		
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			ret.setSituacao(false);
			ret.setDescricao(e.getMessage());
			return ret;
		}
	}
	
	
	
	public RetornoDto<CadastroUsuarioPostDto> consultarUsuarioRh(String cpf) {
		RetornoDto<CadastroUsuarioPostDto>  ret = new RetornoDto<CadastroUsuarioPostDto>();
		CadastroUsuarioPostDto usuario;
		try {
			usuario = this.usuarioDao.consultaUsuarioRhPorCpf(cpf);
			ret.setResultado(usuario);		
			return ret;
		} catch (SQLException e) {
			e.printStackTrace();
			ret.setSituacao(false);
			ret.setDescricao(e.getMessage());
			return ret;
		}
	}
	
	/**
	 * Apenas lista os perfis mais importanes
	 * @param login
	 * @return
	 */
	public RetornoDto<List<PerfilDto>> listarPerfisPorUsuarios(String login){
		/*RetornoDto<List<PerfilDto>>  ret = new RetornoDto<List<PerfilDto>>();
		List<PerfilDto> listaUsuario;
		try {
			//listaUsuario = this.usuarioDao.listarPerfisPorUsuarios(login);			
			//ret.setResultado(listaUsuario);		
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret.setSituacao(false);
			ret.setDescricao(e.getMessage());
			return ret;
		}*/
		return null;
	}
	
	
	public RetornoDto<List<PerfilDto>> listarApenasPerfisImportantesPorLogin(String login){
		/*RetornoDto<List<PerfilDto>>  ret = new RetornoDto<List<PerfilDto>>();
		List<PerfilDto> listaUsuario;
		try {
		//	listaUsuario = this.usuarioDao.listarApenasPerfisImportantesPorLogin(login);			
			ret.setResultado(listaUsuario);		
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ret.setSituacao(false);
			ret.setDescricao(e.getMessage());
			return ret;
		}*/
		return null;
	}

	
	@Deprecated
	public static RetornoApiEprocessoDto realizarCadastroUsuarioNoProcessoEletronico(CadastroUsuarioPostDto atendimento) {
		/*ApiUsuario apiUsuario = new ApiUsuario();
		String v = ""; 
		v = apiUsuario.cadastrarUsuario(atendimento);
		
		ApiAjustaPerfil ajusta = new ApiAjustaPerfil();
		
	//	ajusta.deletarSetor(atendimento.getUsuario());
		
		ApiInsereSetor insere = new ApiInsereSetor();
		
		insere.adicionarSetor(atendimento);

		

		//verificar se houve algum erro no sistema, caso ocorra uma erro o sistema ira salvar
		if(!v.contains("erro")){
			//AtendimentoModel atendimentoModel = Padrao.converter(atendimento);
		//	atendimentoRepository.save(atendimentoModel);
		}
		RetornoApiEprocessoDto r = Converter.convertJsonToDTO(v);*/
		return null;

	}


	public void registrarUsuarioNoSistema(CadastroUsuarioPostDto usuario) {
		UsuarioCadastrado usuarioCadatrado = new UsuarioCadastrado();
		usuarioCadatrado.setCdUsuario(usuario.getCdUsuario());
		usuarioCadatrado.setDsNome(usuario.getNmUsuario());
		usuarioCadatrado.setDsProntuario(usuario.getDsProntuario());
		usuarioCadatrado.setNrCpf(usuario.getNuCpfcnpj());
		usuarioCadastradoRepository.save(usuarioCadatrado);		
	}


	public RetornoDto<ConsultaUsuarioDTO> consultarUsuarioNoProcessoEletronico(String cdUsuario) {
		
		ApiUsuarioSetores usuarioApi = new ApiUsuarioSetores();
		usuarioApi.pesquisarSetor(cdUsuario, "UNIDADES");
		
		return null;
		
	}


	





	
	
	
	
}
