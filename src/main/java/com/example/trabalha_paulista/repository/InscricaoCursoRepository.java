package com.example.trabalha_paulista.repository;

import com.example.trabalha_paulista.models.InscricaoCurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InscricaoCursoRepository extends JpaRepository<InscricaoCurso, Long> {
    boolean existsByAlunoIdAndCursoId(Long alunoId, Long cursoId);

    Optional<InscricaoCurso> findByAlunoIdAndCursoId(Long alunoId, Long cursoId);
}
