package com.example.trabalha_paulista.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CursoRequest(
        @NotBlank(message = "O nome do curso e obrigatorio") String nome,
        @NotBlank(message = "A descricao do curso e obrigatoria") String descricao,
        @NotBlank(message = "A instituicao e obrigatoria") String instituicao,
        @NotNull(message = "A carga horaria e obrigatoria") @Positive(message = "A carga horaria deve ser maior que zero") Integer cargaHoraria
) {
}
