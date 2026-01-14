package br.com.prefeitura.usuario.main.exonerados2;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    public void enviarRelatorioExonerados(String corpo) {
        enviar(corpo, "Relatório de Usuários Exonerados","suporte@diadema.sp.gov.br");
    }
    
    public void enviarRelatorioExoneradosEprocessos(String corpo) {
        enviar(corpo, "Relatório de Usuários Exonerados no processo eletronico" , "eprocesso.suporte@diadema.sp.gov.br");
    }

    public void enviarRelatorioAfastados(String corpo) {
        enviar(corpo, "Relatório de Usuários Afastados ou Licenciados", "suporte@diadema.sp.gov.br");
    }

    private void enviar(String corpo, String assunto, String destinatario) {
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
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
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
