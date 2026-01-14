package br.com.prefeitura.usuario.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.prefeitura.usuario.Config;
import br.com.prefeitura.usuario.api.solar.ApiCallSolar;
import br.com.prefeitura.usuario.dto.CadastroSetorPostDto;
import br.com.prefeitura.usuario.dto.CadastroUsuarioPostDto;

class Setor{
	String sigla;
	boolean padrao;
	
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public boolean isPadrao() {
		return padrao;
	}
	public void setPadrao(boolean padrao) {
		this.padrao = padrao;
	}
	
	
}

public class ApiCadastrarUsuario extends ApiCallSolar {
	
	
    
    /*public ApiUsuario(String chave) {
    	super("https://eprocesso.diadema.sp.gov.br/solarbpm-integracao/usuario/cadastrar-usuario", chave);
    }*/
    
    public ApiCadastrarUsuario() {
    	//super("https://eprocesso.diadema.sp.gov.br/solarbpm-integracao/usuario/cadastrar-usuario");
    	super(Config.get("solar.url.inserir.usuario"));
    	//solar.url.inserir.usuario = https://eprocesso.diadema.sp.gov.br/solarbpm-integracao/usuario/cadastrar-usuario
    }
    
    private Map<String, Object> criarParametros(CadastroUsuarioPostDto usuarioDto) {
        Map<String, Object> params = new HashMap<>();
        params.put("cdUsuario", usuarioDto.getCdUsuario());
        params.put("deEmail", usuarioDto.getDeEmail());
        params.put("dtAtivacao",usuarioDto.getDtAtivacao());
        params.put("nmUsuario", usuarioDto.getNmUsuario());
        params.put("nuCpfcnpj", usuarioDto.getNuCpfcnpj());
       // params.put("nuProntuarioFuncional", usuarioDto.getNuProntuarioFuncional());
       // params.put("nuProntuarioMedico", usuarioDto.getNuProntuarioMedico());
        
        List<Setor> listaSetores = new ArrayList<Setor>();
        for(CadastroSetorPostDto s : usuarioDto.getSetores()){
        	 Setor se = new Setor();
        	 se.setPadrao(s.getPadrao());
        	 se.setSigla(s.getSgOrgaoSetor());
        	listaSetores.add(se);
        }
        
       

        
        
        params.put("perfis", usuarioDto.getPerfis());
        params.put("setores", listaSetores);
        params.put("sgOrgaoEmpresa", ""); //usuarioDto.getSgOrgaoEmpresa());
        return params;
    }
    
    public String cadastrarUsuario(CadastroUsuarioPostDto usuarioDto) {
        disable();
        
        //Adicionar o setor para a lista
        List<CadastroSetorPostDto> setores = new ArrayList<CadastroSetorPostDto>();
        String sgOrbaos = usuarioDto.getSetor().getSgOrgaoSetor();
        
        sgOrbaos = sgOrbaos.replace("PMDI - ", "");
        sgOrbaos = sgOrbaos.replace("IPRED - ", "");
        sgOrbaos = sgOrbaos.replace("FFF - ", "");
        
        usuarioDto.getSetor().setSgOrgaoSetor(sgOrbaos);
        
        setores.add(usuarioDto.getSetor());
        usuarioDto.setSetores(setores);
        
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = sdf.format(new Date());
        
        usuarioDto.setPerfis(atribuirPerfisPrimeiroCadastro());
        usuarioDto.setDtAtivacao(formattedDate); 
        
        if(usuarioDto.getSetores() != null){
        	for(int i = 0; i < usuarioDto.getSetores().size(); i++){
        		if(i==0){
        			usuarioDto.getSetores().get(i).setPadrao(true);
        		}else{
        			usuarioDto.getSetores().get(i).setPadrao(false);
        		}
        	}
        }
        
        
        Map<String, Object> parametros = criarParametros(usuarioDto);
        
        StringBuilder response = executar(parametros );
        
        
        
        return response.toString();
    }
    
    private List<String> atribuirPerfisPrimeiroCadastro(){
    	List<String> perfisAux = new ArrayList<String>();
    	perfisAux.add("CONSULTA_PROCESSOS");
		perfisAux.add("CPA_CADASTRO_DIGITAL");
		perfisAux.add("CPA_FILTRA_DADOS_PRC");
		perfisAux.add("CPA_RELATORIOS");
		perfisAux.add("CPA_TRAMITADOR");
		perfisAux.add("CPA_VISU_PROC_CADAST");
		perfisAux.add("CPA_VISU_PROC_HIERAR");
		perfisAux.add("CPA_VISU_PROC_SETOR");
		perfisAux.add("CPA_VISU_PROC_TODOS");
		perfisAux.add("WFL_ADMIN_FILA");
    	return  perfisAux; 
    }
    
    
	public static void main(String args[]) {
		List<String> perfisAux = new ArrayList<String>();
		List<CadastroSetorPostDto> setores = new ArrayList<CadastroSetorPostDto>();
		
		perfisAux.add("CONSULTA_PROCESSOS");
		perfisAux.add("CPA_CADASTRO_DIGITAL");
		perfisAux.add("CPA_FILTRA_DADOS_PRC");
		perfisAux.add("CPA_RELATORIOS");
		perfisAux.add("CPA_TRAMITADOR");
		perfisAux.add("CPA_VISU_PROC_CADAST");
		perfisAux.add("CPA_VISU_PROC_HIERAR");
		perfisAux.add("CPA_VISU_PROC_SETOR");
		perfisAux.add("WFL_ADMIN_FILA");

		
		CadastroUsuarioPostDto usuarioDto = new CadastroUsuarioPostDto();
		usuarioDto.setCdUsuario("jessica.fsantos");
		usuarioDto.setDeEmail("jessica.fsantos@diadema.sp.gov.br");
		usuarioDto.setDtAtivacao("23/02/2024");
		usuarioDto.setNmUsuario("JESSICA FREITAS SANTOS");
		usuarioDto.setNuCpfcnpj("22905610883");
		//usuarioDto.setSgOrgaoEmpresa("");
		
		
		CadastroSetorPostDto e = new CadastroSetorPostDto();
		e.setSgOrgaoSetor("SE");
		//e.setPadrao("true");		
		setores.add(e );
		//usuarioDto.setSetores(setores );
		//usuarioDto.setPerfis(perfisAux);
		
		//ConverterDtoJson.mostarJson(usuarioDto);
		
		// Sete outras informações do usuário conforme necessário
		//ApiUsuario apiUsuario = new ApiUsuario();
		//apiUsuario.cadastrarUsuario(usuarioDto);
	}
}
