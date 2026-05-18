package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "parcerias")
public class Parceria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_parceria;

    private String nome_empresa;
    
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    private String contato;
}