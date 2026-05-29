package com.example.trabalha_paulista.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioUpdateRequest(
        @NotBlank(message = "O nome e obrigatorio") String nome,
        @NotBlank(message = "O email e obrigatorio") @Email(message = "Email invalido") String email,
        String senha,
        String telefone,
        @NotBlank(message = "O tipo de usuario e obrigatorio")
        @Pattern(regexp = "candidato|empreendedor", message = "O tipo de usuario deve ser candidato ou empreendedor")
        String tipoUsuario
) {
}
