package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "parcerias")
public class Parceria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_parceria;

    @NotBlank(message = "O nome da empresa é obrigatório")
    private String nome_empresa;
    
    @NotBlank(message = "A descrição da parceria é obrigatória")
    @Column(columnDefinition = "TEXT")
    private String descricao;
    
    @NotBlank(message = "O contato é obrigatório")
    private String contato;
}