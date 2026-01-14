package br.com.prefeitura.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.prefeitura.usuario.model.UsuarioCadastrado;

@Repository
public abstract interface UsuarioCadastradoRepository extends JpaRepository<UsuarioCadastrado, String>{
}
