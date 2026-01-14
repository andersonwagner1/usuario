package br.com.prefeitura.usuario.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.prefeitura.usuario.dto.CadastroUsuarioPostDto;
import br.com.prefeitura.usuario.dto2.AtendimentoDto;

public class EmailService {

	private void enviarEmail(String para, String assunto, String corpo) {
		// Configurações do servidor SMTP
		final String REMETENTE = "sistema.dtic@diadema.sp.gov.br";
		final String SENHA = "ejW6FXv5b@U[1@xSGUU1V6pF3gZGWx*HRF?g?]*UKCUGWT"; // cuidado
																				// com
																				// segurança
																				// aqui

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "mail.diadema.sp.gov.br"); // Ex:
																// smtp.gmail.com
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(REMETENTE, SENHA);
					}
				});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(REMETENTE));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(para));
			message.setSubject(assunto);
			message.setText(corpo);

			Transport.send(message);

			System.out.println("E-mail enviado com sucesso para " + para);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

	

	public void enviarRelatorioExclusao(String corpo) {
		enviar(corpo, "Relatório de Usuários Exonerados");
	}

	public void enviarRelatorioAfastados(String corpo) {
		enviar(corpo, "Relatório de Usuários Afastados ou Licenciados");
	}

	private void enviar(String corpo, String assunto) {
		final String remetente = "sistema.dtic@diadema.sp.gov.br";
		final String senha = "ejW6FXv5b@U[1@xSGUU1V6pF3gZGWx*HRF?g?]*UKCUGWT";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "mail.diadema.sp.gov.br");
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(remetente, senha);
					}
				});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(remetente));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("suporte@diadema.sp.gov.br"));
			message.setSubject(assunto);
			message.setContent(corpo, "text/html; charset=utf-8");
			Transport.send(message);
			System.out.println("E-mail de '" + assunto
					+ "' enviado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
