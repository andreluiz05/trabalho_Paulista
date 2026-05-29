package com.example.trabalha_paulista.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "mentorias")
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mentoria")
    private Long id;

    @NotBlank(message = "O tema da mentoria e obrigatorio")
    @Column(nullable = false)
    private String tema;

    @NotBlank(message = "A descricao da mentoria e obrigatoria")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotNull(message = "A data da mentoria e obrigatoria")
    @Column(nullable = false)
    private LocalDate data;

    @NotBlank(message = "O mentor e obrigatorio")
    @Column(nullable = false)
    private String mentor;
}
