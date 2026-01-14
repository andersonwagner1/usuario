package br.com.prefeitura.usuario.main.exonerados;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    public void enviarRelatorioExclusao(String corpo) {
        enviar(corpo, "Relatório de Usuários Exonerados");
    }

    public void enviarRelatorioAfastados(String corpo) {
        enviar(corpo, "Relatório de Usuários Afastados ou Licenciados");
    }

    private void enviar(String corpo, String assunto) {
        // ATENÇÃO: As credenciais de e-mail foram mantidas, mas devem ser tratadas como segredo
        // e idealmente carregadas de forma mais segura (ex: variáveis de ambiente, Vault).
        final String remetente = "sistema.dtic@diadema.sp.gov.br";
        final String senha = "ejW6FXv5b@U[1@xSGUU1V6pF3gZGWx*HRF?g?]*UKCUGWT";
        
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.diadema.sp.gov.br");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remetente));
            // O destinatário foi mantido como no código original
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("eprocesso.suporte@diadema.sp.gov.br"));
            message.setSubject(assunto);
            message.setContent(corpo, "text/html; charset=utf-8");
            Transport.send(message);
            System.out.println("E-mail de '" + assunto + "' enviado com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao enviar e-mail: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
