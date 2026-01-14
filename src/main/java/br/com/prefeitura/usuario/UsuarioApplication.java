package br.com.prefeitura.usuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "br.com.prefeitura.usuario")
//@SpringBootApplication(
@SpringBootApplication(exclude={SecurityAutoConfiguration.class}) //desbiiltar a segurança
//@SpringBootApplication(exclude={SecurityConfiguration.class}) //desbiiltar a segurança

public class UsuarioApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(UsuarioApplication.class, args);
	}
}
