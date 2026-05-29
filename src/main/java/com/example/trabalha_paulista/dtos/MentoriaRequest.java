package com.example.trabalha_paulista.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MentoriaRequest(
        @NotBlank(message = "O tema da mentoria e obrigatorio") String tema,
        @NotBlank(message = "A descricao da mentoria e obrigatoria") String descricao,
        @NotNull(message = "A data da mentoria e obrigatoria") LocalDate data,
        @NotBlank(message = "O mentor e obrigatorio") String mentor
) {
}
