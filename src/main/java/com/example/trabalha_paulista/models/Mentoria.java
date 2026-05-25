package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
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
    private Long id_mentoria;

    @NotBlank(message = "O tema da mentoria é obrigatório")
    private String tema;
    
    @NotBlank(message = "A descrição da mentoria é obrigatória")
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @NotNull(message = "A data da mentoria é obrigatória")
    private LocalDate data;

    @NotBlank(message = "O mentor é obrigatório")
    private String mentor;
}