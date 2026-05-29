package com.example.trabalha_paulista.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "parcerias")
public class Parceria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parceria")
    private Long id;

    @NotBlank(message = "O nome da empresa e obrigatorio")
    @Column(name = "nome_empresa", nullable = false)
    private String nomeEmpresa;

    @NotBlank(message = "A descricao da parceria e obrigatoria")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotBlank(message = "O contato e obrigatorio")
    @Column(nullable = false)
    private String contato;
}
