package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
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
    private Long id_curso;

    @NotBlank(message = "O nome do curso é obrigatório")
    private String nome;
    
    @NotBlank(message = "A descrição do curso é obrigatória")
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @NotBlank(message = "A instituição é obrigatória")
    private String instituicao;

    @NotNull(message = "A carga horária é obrigatória")
    @Positive(message = "A carga horária deve ser maior que zero")
    private Integer carga_horaria;
}