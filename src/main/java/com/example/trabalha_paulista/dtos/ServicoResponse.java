package com.example.trabalha_paulista.dtos;

import com.example.trabalha_paulista.models.Servico;

import java.math.BigDecimal;

public record ServicoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        String categoria,
        UsuarioResponse anunciante
) {
    public static ServicoResponse from(Servico servico) {
        return new ServicoResponse(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getPreco(),
                servico.getCategoria(),
                UsuarioResponse.from(servico.getAnunciante())
        );
    }
}
