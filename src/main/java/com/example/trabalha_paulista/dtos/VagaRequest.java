package com.example.trabalha_paulista.dtos;

import jakarta.validation.constraints.NotBlank;

public record VagaRequest(
        @NotBlank(message = "O titulo da vaga e obrigatorio") String titulo,
        @NotBlank(message = "A descricao da vaga e obrigatoria") String descricao,
        @NotBlank(message = "O nome da empresa e obrigatorio") String empresa,
        @NotBlank(message = "A cidade e obrigatoria") String cidade,
        @NotBlank(message = "O tipo de vaga e obrigatorio") String tipoVaga,
        Long publicadorId
) {
}
