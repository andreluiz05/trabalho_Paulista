package com.example.trabalha_paulista.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "vagas")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O titulo da vaga e obrigatorio")
    @Column(nullable = false)
    private String titulo;

    @NotBlank(message = "A descricao da vaga e obrigatoria")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @NotBlank(message = "O nome da empresa e obrigatorio")
    @Column(nullable = false)
    private String empresa;

    @NotBlank(message = "A cidade e obrigatoria")
    @Column(nullable = false)
    private String cidade;

    @NotBlank(message = "O tipo de vaga e obrigatorio")
    @Column(name = "tipo_vaga", nullable = false)
    private String tipoVaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario publicador;
}
