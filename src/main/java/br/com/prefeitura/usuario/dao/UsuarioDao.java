package br.com.prefeitura.usuario.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.prefeitura.usuario.dao.conexao.OracleAbaco;
import br.com.prefeitura.usuario.dao.conexao.OracleMobile;
import br.com.prefeitura.usuario.dto.CadastroUsuarioPostDto;
import br.com.prefeitura.usuario.dto2.UsuarioExcluirAfastadoDto;

public class UsuarioDao {

	private OracleAbaco connectionAbaco = new OracleAbaco();
	private OracleMobile connectionMobile = new OracleMobile();
	

	public CadastroUsuarioPostDto consultaUsuarioRhPorCpf(String cpf)
			throws SQLException {
		if (cpf != null) {
			cpf = cpf.replaceAll("[^0-9]", "");
		}

		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CTR.CONTRATOMATRICULANUMERICO, ");
		sql.append(" PEQ.PESNOME, ");
		sql.append(" PEF.PFCPF, ");
		sql.append(" ( ");
		sql.append(" CASE ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 1 THEN 'Em Exercício' ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 2 THEN 'Afastado ou Licenciado' ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 3 THEN 'Férias' ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 4 THEN 'disposição da Prefeitura' ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 5 THEN 'Exonerado' ");
		sql.append(" END     ");
		sql.append(" ) as situacaoRh, ");
		sql.append(" CTR.CONTRATOSTATUS ");
		sql.append(" FROM   ETURMALINA.TBCONTRATO          CTR, ");
		sql.append(" ETURMALINA.TBPESSOA            PES, ");
		sql.append(" SISBASE.TBPESSOA_Q             PEQ, ");
		sql.append(" SISBASE.TBPESSOAFISICA         PEF ");
		sql.append(" WHERE  CTR.SERVIDORID = PES.SERVIDORID ");
		sql.append(" AND    PES.PESSERVIDORID = PEQ.PESID ");
		sql.append(" AND    PEF.PFID = PES.PESSERVIDORID ");
		sql.append(" AND  PEF.PFCPF = '" + cpf + "'");
		sql.append(" ORDER BY CONTRATOSTATUS  ASC, CONTRATOMATRICULANUMERICO  desc ");

		ResultSet rs = connectionAbaco.executeQuery(sql.toString());
		List<CadastroUsuarioPostDto> usuarios = new ArrayList<CadastroUsuarioPostDto>();

		if (rs.next()) {
			CadastroUsuarioPostDto usuario = new CadastroUsuarioPostDto();
			usuario.setDeInformacoes(rs.getString("CONTRATOMATRICULANUMERICO"));
			usuario.setNmUsuario(rs.getString("PESNOME"));
			usuario.setNuCpfcnpj(rs.getString("PFCPF"));
			usuario.setIcSituacaoRh(rs.getString("situacaoRh"));
			usuario.setDsProntuario(rs.getString("CONTRATOMATRICULANUMERICO"));
			usuarios.add(usuario);
			return usuario;
		}
		return null;

	}

	public List<UsuarioExcluirAfastadoDto> listarUsuarioExoneradosRh()
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("  WITH BaseContratos AS ( ");
		sql.append("  SELECT  ");
		sql.append("  PEF.PFCPF, ");
		sql.append("  PEQ.PESNOME, ");
		sql.append("  CTR.CONTRATOMATRICULANUMERICO, ");
		sql.append("  CTR.CONTRATOSTATUS ");
		sql.append("  FROM ETURMALINA.TBCONTRATO      CTR ");
		sql.append("  JOIN ETURMALINA.TBPESSOA        PES ON CTR.SERVIDORID = PES.SERVIDORID ");
		sql.append("  JOIN SISBASE.TBPESSOA_Q         PEQ ON PES.PESSERVIDORID = PEQ.PESID ");
		sql.append("  JOIN SISBASE.TBPESSOAFISICA     PEF ON PEF.PFID = PES.PESSERVIDORID ");
		sql.append("  WHERE NOT EXISTS ( ");
		sql.append("  SELECT 1 FROM USUARIOS_DESATIVADO UD ");
		sql.append("  WHERE UD.NRCPF = PEF.PFCPF AND UD.ICTIPO = 'EXONERADOS' ");
		sql.append("  ) ");
		sql.append("  ) ");
		sql.append("  SELECT ");
		sql.append("  LISTAGG(CONTRATOMATRICULANUMERICO, ', ') WITHIN GROUP (ORDER BY CONTRATOMATRICULANUMERICO) AS CONTRATOS, ");
		sql.append("  PESNOME, ");
		sql.append("  PFCPF, ");
		sql.append("  5 AS CONTRATOSTATUS ");
		sql.append("  FROM BaseContratos ");
		sql.append("  GROUP BY PESNOME, PFCPF ");
		sql.append("  HAVING COUNT(CASE WHEN CONTRATOSTATUS = 5 THEN 1 END) > 0 ");
		sql.append("  AND COUNT(CASE WHEN CONTRATOSTATUS IN (1,2,3,4) THEN 1 END) = 0");

		ResultSet rs = connectionMobile.executeQuery(sql.toString());
		List<UsuarioExcluirAfastadoDto> usuarios = new ArrayList<UsuarioExcluirAfastadoDto>();

		while (rs.next()) {
			UsuarioExcluirAfastadoDto usuario = new UsuarioExcluirAfastadoDto(
					rs.getString("CONTRATOS"), rs.getString("PESNOME"),
					rs.getString("PFCPF"));
			usuarios.add(usuario);

		}
		return usuarios;

	}

	public List<CadastroUsuarioPostDto> listarUsuarioNoRhPorNome(String nome)
			throws SQLException {
		nome = nome.replace("*", "%");
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT CTR.CONTRATOMATRICULANUMERICO, ");
		sql.append(" PEQ.PESNOME, ");
		sql.append(" PEF.PFCPF, ");
		sql.append(" ( ");
		sql.append(" CASE ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 1 THEN 'Em Exercício' ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 2 THEN 'Afastado ou Licenciado' ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 3 THEN 'Férias' ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 4 THEN 'disposição da Prefeitura' ");
		sql.append(" WHEN CTR.CONTRATOSTATUS = 5 THEN 'Exonerado' ");
		sql.append(" END     ");
		sql.append(" ) as situacaoRh, ");
		sql.append(" CTR.CONTRATOSTATUS ");
		sql.append(" FROM   ETURMALINA.TBCONTRATO          CTR, ");
		sql.append(" ETURMALINA.TBPESSOA            PES, ");
		sql.append(" SISBASE.TBPESSOA_Q             PEQ, ");
		sql.append(" SISBASE.TBPESSOAFISICA         PEF ");
		sql.append(" WHERE  CTR.SERVIDORID = PES.SERVIDORID ");
		sql.append(" AND    PES.PESSERVIDORID = PEQ.PESID ");
		sql.append(" AND    PEF.PFID = PES.PESSERVIDORID ");
		sql.append(" AND  PEQ.PESNOME like '" + nome + "'");
		sql.append(" ORDER BY CONTRATOSTATUS  ASC, CONTRATOMATRICULANUMERICO  desc ");

		ResultSet rs = connectionAbaco.executeQuery(sql.toString());
		List<CadastroUsuarioPostDto> usuarios = new ArrayList<CadastroUsuarioPostDto>();

		while (rs.next()) {
			CadastroUsuarioPostDto usuario = new CadastroUsuarioPostDto();
			usuario.setDeInformacoes(rs.getString("CONTRATOMATRICULANUMERICO"));
			usuario.setNmUsuario(rs.getString("PESNOME"));
			usuario.setNuCpfcnpj(rs.getString("PFCPF"));
			usuario.setIcSituacaoRh(rs.getString("situacaoRh"));
			usuario.setDsProntuario(rs.getString("CONTRATOMATRICULANUMERICO"));
			usuarios.add(usuario);

		}
		return usuarios;

	}

	public List<UsuarioExcluirAfastadoDto> listarUsuariosAfastadosNoRH()
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("   WITH BaseContratos AS ( ");
		sql.append("   SELECT   ");
		sql.append("   PEF.PFCPF,  ");
		sql.append("   PEQ.PESNOME,  ");
		sql.append("   CTR.CONTRATOMATRICULANUMERICO, ");
		sql.append("   CTR.CONTRATOSTATUS  ");
		sql.append("   FROM ETURMALINA.TBCONTRATO      CTR ");
		sql.append("   JOIN ETURMALINA.TBPESSOA        PES ON CTR.SERVIDORID = PES.SERVIDORID ");
		sql.append("   JOIN SISBASE.TBPESSOA_Q         PEQ ON PES.PESSERVIDORID = PEQ.PESID  ");
		sql.append("   JOIN SISBASE.TBPESSOAFISICA     PEF ON PEF.PFID = PES.PESSERVIDORID  ");
		sql.append("   WHERE NOT EXISTS (  ");
		sql.append("   SELECT 1 FROM USUARIOS_DESATIVADO UD ");
		sql.append("   WHERE UD.NRCPF = PEF.PFCPF AND UD.ICTIPO = 'AFASTADO' ");
		sql.append("   )  ");
		sql.append("   )  ");
		sql.append("   SELECT   ");
		sql.append("   LISTAGG(CONTRATOMATRICULANUMERICO, ', ') WITHIN GROUP (ORDER BY CONTRATOMATRICULANUMERICO) AS CONTRATOS, ");
		sql.append("   PESNOME,  ");
		sql.append("   PFCPF,  ");
		sql.append("   5 AS CONTRATOSTATUS ");
		sql.append("   FROM BaseContratos  ");
		sql.append("   GROUP BY PESNOME, PFCPF  ");
		sql.append("   HAVING COUNT(CASE WHEN CONTRATOSTATUS = 2 THEN 1 END) > 0 ");
		sql.append("   AND COUNT(CASE WHEN CONTRATOSTATUS IN (1,3,4,5) THEN 1 END) = 0 ");

		ResultSet rs = connectionMobile.executeQuery(sql.toString());
		List<UsuarioExcluirAfastadoDto> usuarios = new ArrayList<UsuarioExcluirAfastadoDto>();

		while (rs.next()) {
			UsuarioExcluirAfastadoDto usuario = new UsuarioExcluirAfastadoDto(
					rs.getString("CONTRATOS"), rs.getString("PESNOME"),
					rs.getString("PFCPF"));
			usuarios.add(usuario);

		}
		return usuarios;

	}

	public List<UsuarioExcluirAfastadoDto> removerUsuariosDosExcluidos()
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT nrcpf ");
		sql.append(" FROM USUARIOS_DESATIVADO");
		sql.append(" WHERE NRCPF IN");
		sql.append(" (SELECT PEF.PFCPF");
		sql.append(" FROM ETURMALINA.TBCONTRATO CTR");
		sql.append(" JOIN ETURMALINA.TBPESSOA PES ON CTR.SERVIDORID = PES.SERVIDORID");
		sql.append(" JOIN SISBASE.TBPESSOA_Q PEQ ON PES.PESSERVIDORID = PEQ.PESID");
		sql.append(" JOIN SISBASE.TBPESSOAFISICA PEF ON PEF.PFID = PES.PESSERVIDORID");
		sql.append(" AND CONTRATOSTATUS IN (1, 2, 3, 4))");

		ResultSet rs = connectionMobile.executeQuery(sql.toString());
		List<UsuarioExcluirAfastadoDto> usuarios = new ArrayList<UsuarioExcluirAfastadoDto>();

		while (rs.next()) {
			UsuarioExcluirAfastadoDto usuario = new UsuarioExcluirAfastadoDto(
					"", "", rs.getString("nrcpf"));
			usuarios.add(usuario);

		}
		return usuarios;

	}

}
