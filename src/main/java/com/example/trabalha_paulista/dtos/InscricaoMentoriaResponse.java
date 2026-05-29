package com.example.trabalha_paulista.dtos;

import com.example.trabalha_paulista.models.InscricaoMentoria;

import java.time.LocalDate;

public record InscricaoMentoriaResponse(
        Long id,
        Long participanteId,
        String participanteNome,
        Long mentoriaId,
        String mentoriaTema,
        LocalDate dataInscricao
) {
    public static InscricaoMentoriaResponse from(InscricaoMentoria inscricao) {
        return new InscricaoMentoriaResponse(
                inscricao.getId(),
                inscricao.getParticipante().getId(),
                inscricao.getParticipante().getNome(),
                inscricao.getMentoria().getId(),
                inscricao.getMentoria().getTema(),
                inscricao.getDataInscricao()
        );
    }
}
