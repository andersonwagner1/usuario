package br.com.prefeitura.usuario.main.exonerados2;

import java.util.List;
import java.util.stream.Collectors;

import br.com.prefeitura.usuario.dto2.UsuarioExcluirAfastadoDto;

public class FormataCorpoEmail {
      public String formatarEmail(List<UsuarioExcluirAfastadoDto> users, String motivo) {
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
      
      
      public String formatarEmailProcessoEletronico(List<UsuarioExcluirAfastadoDto> users, String motivo) {
          String tabelaHtml = users.stream()
              .map(user -> String.format("<tr><td style='padding:8px;'>%s</td><td style='padding:8px;'>%s</td><td style='padding:8px;'>%s</td></tr>", 
                                         user.getMatricula(), user.getNome(), user.getCpf()))
              .collect(Collectors.joining());

          return String.format(
              "<html><body><p>Olá,</p><p>A verificação automática identificou os seguintes usuários que estão ativo no processo eletronioco que precisa ser inativados %s:</p>" +
              "<table border='1' style='border-collapse:collapse;width:90%%;'><thead><tr><th style='padding:8px;background-color:#f2f2f2;'>Matrículas</th><th style='padding:8px;background-color:#f2f2f2;'>Nome</th><th style='padding:8px;background-color:#f2f2f2;'>CPF</th></tr></thead><tbody>%s</tbody></table>" +
              "<p><br>Atenciosamente,<br>Sistema de Verificação Automática.</p></body></html>",
              motivo, tabelaHtml
          );
      }
}
