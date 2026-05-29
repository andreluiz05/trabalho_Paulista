package com.example.trabalha_paulista.dtos;

import com.example.trabalha_paulista.models.Parceria;

public record ParceriaResponse(
        Long id,
        String nomeEmpresa,
        String descricao,
        String contato
) {
    public static ParceriaResponse from(Parceria parceria) {
        return new ParceriaResponse(parceria.getId(), parceria.getNomeEmpresa(), parceria.getDescricao(), parceria.getContato());
    }
}
