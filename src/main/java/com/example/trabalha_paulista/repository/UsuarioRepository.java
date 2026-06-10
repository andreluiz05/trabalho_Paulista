package com.example.trabalha_paulista.repository;

import com.example.trabalha_paulista.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
