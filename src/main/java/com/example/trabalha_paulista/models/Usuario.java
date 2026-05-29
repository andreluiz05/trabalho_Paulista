package com.example.trabalha_paulista.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data 
@Entity 
@Table(name = "usuarios") 
public class Usuario {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    @Column(unique = true) // Regra do DER: email deve ser único
    private String email;
    
    @NotBlank(message = "A senha é obrigatória")
    private String senha;
    
    private String telefone;

    @NotBlank(message = "O tipo de usuário é obrigatório")
    private String tipo_usuario;
}