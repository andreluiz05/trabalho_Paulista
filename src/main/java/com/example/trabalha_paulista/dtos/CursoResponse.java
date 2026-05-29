package com.example.trabalha_paulista.dtos;

import com.example.trabalha_paulista.models.Curso;

public record CursoResponse(
        Long id,
        String nome,
        String descricao,
        String instituicao,
        Integer cargaHoraria
) {
    public static CursoResponse from(Curso curso) {
        return new CursoResponse(curso.getId(), curso.getNome(), curso.getDescricao(), curso.getInstituicao(), curso.getCargaHoraria());
    }
}
