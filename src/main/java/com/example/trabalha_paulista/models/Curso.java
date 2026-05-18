package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    private String instituicao;
    private Integer carga_horaria;
}