package com.example.trabalha_paulista.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ServicoRequest(
        @NotBlank(message = "O nome do servico e obrigatorio") String nome,
        String descricao,
        @PositiveOrZero(message = "O preco nao pode ser negativo") BigDecimal preco,
        String categoria,
        @NotNull(message = "O anunciante e obrigatorio") Long anuncianteId
) {
}
