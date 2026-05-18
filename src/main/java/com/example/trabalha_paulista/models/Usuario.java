package com.example.trabalha_paulista.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Data 
@Entity 
@Table(name = "usuarios") 
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nome;
    
    @Column(unique = true) // Regra do DER: email deve ser único
    private String email;
    
    private String senha;
    
    private String telefone;
    private String tipo_usuario;
}