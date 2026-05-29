package com.example.trabalha_paulista.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long id;

    @NotBlank(message = "O nome do curso e obrigatorio")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "A descricao do curso e obrigatoria")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotBlank(message = "A instituicao e obrigatoria")
    @Column(nullable = false)
    private String instituicao;

    @NotNull(message = "A carga horaria e obrigatoria")
    @Positive(message = "A carga horaria deve ser maior que zero")
    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;
}
