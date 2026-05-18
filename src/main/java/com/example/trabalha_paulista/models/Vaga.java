package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "vagas")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Mantendo o padrão 'id' para não dar dor de cabeça!

    private String titulo;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    private String empresa;
    private String cidade;
    private String tipo_vaga;

    // Aqui acontece a mágica da Chave Estrangeira (FK)!
    @ManyToOne
    @JoinColumn(name = "id_usuario") 
    private Usuario publicador;
}