package com.example.trabalha_paulista.dtos;

import jakarta.validation.constraints.NotNull;

public record InscricaoCursoRequest(
        @NotNull(message = "O aluno e obrigatorio") Long alunoId,
        @NotNull(message = "O curso e obrigatorio") Long cursoId
) {
}
