package com.susan.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.susan.app.entity.Usuario;

@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	public Usuario findByUsername(String username);//Authentication
}