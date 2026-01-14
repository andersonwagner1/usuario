package br.com.prefeitura.usuario.main.exonerados;

import org.springframework.jdbc.core.JdbcTemplate;

public class NotificacaoExoneradoRepository {
    private final JdbcTemplate jdbcTemplate;

    public NotificacaoExoneradoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(String cpf) {
        jdbcTemplate.update("INSERT INTO USUARIOS_EXONERADOS (cpf_servidor) VALUES (?)", cpf);
    }
}
