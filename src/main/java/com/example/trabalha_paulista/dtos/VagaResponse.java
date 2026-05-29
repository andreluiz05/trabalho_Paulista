package com.example.trabalha_paulista.dtos;

import com.example.trabalha_paulista.models.Vaga;

public record VagaResponse(
        Long id,
        String titulo,
        String descricao,
        String empresa,
        String cidade,
        String tipoVaga,
        UsuarioResponse publicador
) {
    public static VagaResponse from(Vaga vaga) {
        UsuarioResponse publicador = vaga.getPublicador() == null ? null : UsuarioResponse.from(vaga.getPublicador());
        return new VagaResponse(vaga.getId(), vaga.getTitulo(), vaga.getDescricao(), vaga.getEmpresa(), vaga.getCidade(), vaga.getTipoVaga(), publicador);
    }
}
