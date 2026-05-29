package com.example.trabalha_paulista.dtos;

import jakarta.validation.constraints.NotBlank;

public record ParceriaRequest(
        @NotBlank(message = "O nome da empresa e obrigatorio") String nomeEmpresa,
        @NotBlank(message = "A descricao da parceria e obrigatoria") String descricao,
        @NotBlank(message = "O contato e obrigatorio") String contato
) {
}
