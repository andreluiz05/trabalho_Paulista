package com.example.trabalha_paulista.repository;

import com.example.trabalha_paulista.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Aqui você já ganha métodos como save(), findAll(), findById() e deleteById()!
}