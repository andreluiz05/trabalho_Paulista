package com.example.trabalha_paulista.dtos;

import jakarta.validation.constraints.NotNull;

public record InscricaoVagaRequest(
        @NotNull(message = "O candidato e obrigatorio") Long candidatoId,
        @NotNull(message = "A vaga e obrigatoria") Long vagaId
) {
}
