package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "inscricoes_cursos")
public class InscricaoCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario aluno;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    private LocalDate data_inscricao;
}