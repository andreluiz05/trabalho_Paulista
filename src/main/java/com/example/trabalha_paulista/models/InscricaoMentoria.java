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
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(
        name = "inscricoes_mentorias",
        uniqueConstraints = @UniqueConstraint(name = "uk_inscricao_mentoria_usuario_mentoria", columnNames = {"id_usuario", "id_mentoria"})
)
public class InscricaoMentoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario participante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mentoria", nullable = false)
    private Mentoria mentoria;

    @Column(name = "data_inscricao", nullable = false)
    private LocalDate dataInscricao;
}
