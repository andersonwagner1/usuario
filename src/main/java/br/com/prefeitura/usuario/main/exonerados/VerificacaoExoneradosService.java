package br.com.prefeitura.usuario.main.exonerados;

import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.stream.Collectors;

public class VerificacaoExoneradosService {
    private final JdbcTemplate jdbcTemplate;
    private final EmailService emailService;
    private final NotificacaoExoneradoRepository repo;

    public VerificacaoExoneradosService(JdbcTemplate jdbcTemplate, EmailService emailService, NotificacaoExoneradoRepository repo) {
        this.jdbcTemplate = jdbcTemplate;
        this.emailService = emailService;
        this.repo = repo;
    }

    public void executarVerificacaoEEnviarEmail() {
        String sql = "SELECT PEF.PFCPF, PEQ.PESNOME, LISTAGG(CTR.CONTRATOMATRICULANUMERICO, ', ') WITHIN GROUP (ORDER BY CTR.CONTRATOMATRICULANUMERICO) AS MATRICULAS FROM ETURMALINA.TBCONTRATO CTR JOIN ETURMALINA.TBPESSOA PES ON CTR.SERVIDORID=PES.SERVIDORID JOIN SISBASE.TBPESSOA_Q PEQ ON PES.PESSERVIDORID=PEQ.PESID JOIN SISBASE.TBPESSOAFISICA PEF ON PEF.PFID=PES.PESSERVIDORID WHERE PEF.PFCPF NOT IN (SELECT t.cpf_servidor FROM USUARIOS_EXONERADOS t) GROUP BY PEF.PFCPF,PEQ.PESNOME HAVING SUM(CASE WHEN CTR.CONTRATOSTATUS=5 THEN 1 ELSE 0 END)>0 AND SUM(CASE WHEN CTR.CONTRATOSTATUS<>5 THEN 1 ELSE 0 END)=0";
        
        List<UsuarioDTO> users = jdbcTemplate.query(sql, (rs, rowNum) -> 
            new UsuarioDTO(rs.getString("MATRICULAS"), rs.getString("PESNOME"), rs.getString("PFCPF"))
        );

        System.out.println("Novos exonerados para notificar: " + users.size());
        
        if (!users.isEmpty()) {
            String corpoEmail = formatarEmail(users, "que possuem apenas o status 'Exonerado'");
            emailService.enviarRelatorioExclusao(corpoEmail);
            
            users.forEach(u -> repo.save(u.getCpf()));
            
            System.out.println(users.size() + " exonerados notificados e registrados no histórico.");
        }
    }

    private String formatarEmail(List<UsuarioDTO> users, String motivo) {
        String tabelaHtml = users.stream()
            .map(user -> String.format("<tr><td style='padding:8px;'>%s</td><td style='padding:8px;'>%s</td><td style='padding:8px;'>%s</td></tr>", 
                                       user.getMatricula(), user.getNome(), user.getCpf()))
            .collect(Collectors.joining());

        return String.format(
            "<html><body><p>Olá,</p><p>A verificação automática identificou os seguintes usuários %s:</p>" +
            "<table border='1' style='border-collapse:collapse;width:90%%;'><thead><tr><th style='padding:8px;background-color:#f2f2f2;'>Matrículas</th><th style='padding:8px;background-color:#f2f2f2;'>Nome</th><th style='padding:8px;background-color:#f2f2f2;'>CPF</th></tr></thead><tbody>%s</tbody></table>" +
            "<p><br>Atenciosamente,<br>Sistema de Verificação Automática.</p></body></html>",
            motivo, tabelaHtml
        );
    }
}
