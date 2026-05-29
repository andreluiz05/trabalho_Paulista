package com.example.trabalha_paulista.dtos;

import com.example.trabalha_paulista.models.InscricaoCurso;

import java.time.LocalDate;

public record InscricaoCursoResponse(
        Long id,
        Long alunoId,
        String alunoNome,
        Long cursoId,
        String cursoNome,
        LocalDate dataInscricao
) {
    public static InscricaoCursoResponse from(InscricaoCurso inscricao) {
        return new InscricaoCursoResponse(
                inscricao.getId(),
                inscricao.getAluno().getId(),
                inscricao.getAluno().getNome(),
                inscricao.getCurso().getId(),
                inscricao.getCurso().getNome(),
                inscricao.getDataInscricao()
        );
    }
}
