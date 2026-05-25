package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "vagas")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Mantendo o padrão 'id' para não dar dor de cabeça!

    @NotBlank(message = "O título da vaga é obrigatório")
    private String titulo;
    
    @NotBlank(message = "A descrição da vaga é obrigatória")
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @NotBlank(message = "O nome da empresa é obrigatório")
    private String empresa;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O tipo de vaga é obrigatório")
    private String tipo_vaga;

    // Aqui acontece a mágica da Chave Estrangeira (FK)!
    @ManyToOne
    @JoinColumn(name = "id_usuario") 
    private Usuario publicador;
}