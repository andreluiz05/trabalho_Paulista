package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "servicos")
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    private Double preco; // Usando Double para valores em dinheiro
    private String categoria;

    // Chave Estrangeira apontando para o Usuário
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario anunciante;
}