package com.example.trabalha_paulista.dtos;

import com.example.trabalha_paulista.models.Mentoria;

import java.time.LocalDate;

public record MentoriaResponse(
        Long id,
        String tema,
        String descricao,
        LocalDate data,
        String mentor
) {
    public static MentoriaResponse from(Mentoria mentoria) {
        return new MentoriaResponse(mentoria.getId(), mentoria.getTema(), mentoria.getDescricao(), mentoria.getData(), mentoria.getMentor());
    }
}
