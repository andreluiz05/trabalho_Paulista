package com.example.trabalha_paulista.dtos;

import com.example.trabalha_paulista.models.InscricaoVaga;

import java.time.LocalDate;

public record InscricaoVagaResponse(
        Long id,
        Long candidatoId,
        String candidatoNome,
        Long vagaId,
        String vagaTitulo,
        LocalDate dataInscricao
) {
    public static InscricaoVagaResponse from(InscricaoVaga inscricao) {
        return new InscricaoVagaResponse(
                inscricao.getId(),
                inscricao.getCandidato().getId(),
                inscricao.getCandidato().getNome(),
                inscricao.getVaga().getId(),
                inscricao.getVaga().getTitulo(),
                inscricao.getDataInscricao()
        );
    }
}
