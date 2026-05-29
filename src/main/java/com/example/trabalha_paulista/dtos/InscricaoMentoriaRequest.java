package com.example.trabalha_paulista.dtos;

import jakarta.validation.constraints.NotNull;

public record InscricaoMentoriaRequest(
        @NotNull(message = "O participante e obrigatorio") Long participanteId,
        @NotNull(message = "A mentoria e obrigatoria") Long mentoriaId
) {
}
