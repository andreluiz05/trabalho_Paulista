package com.example.trabalha_paulista.dtos;

public record AuthResponse(
        String token,
        String tipo,
        UsuarioResponse usuario
) {
}
