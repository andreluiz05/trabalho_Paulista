package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "mentorias")
public class Mentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_mentoria;

    private String tema;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    private LocalDate data;
    private String mentor;
}