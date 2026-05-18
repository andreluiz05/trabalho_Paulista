package com.example.trabalha_paulista.repository;

import com.example.trabalha_paulista.models.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    // Só de herdar o JpaRepository, já ganhamos o save(), findAll(), etc!
}