package com.security.demo.Repository;

import com.security.demo.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRep extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);
}
