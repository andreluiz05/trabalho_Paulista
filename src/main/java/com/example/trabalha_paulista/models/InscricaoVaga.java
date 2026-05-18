package com.example.trabalha_paulista.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "inscricoes_vagas")
public class InscricaoVaga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Chave estrangeira puxando o Usuário
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario candidato;

    // Chave estrangeira puxando a Vaga
    @ManyToOne
    @JoinColumn(name = "id_vaga")
    private Vaga vaga;

    private LocalDate data_inscricao;
}