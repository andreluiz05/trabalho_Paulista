package com.example.trabalha_paulista.dtos;

import com.example.trabalha_paulista.models.Usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String telefone,
        String tipoUsuario
) {
    public static UsuarioResponse from(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getTipoUsuario()
        );
    }
}
