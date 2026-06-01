package com.example.trabalha_paulista.repository;

import com.example.trabalha_paulista.models.InscricaoVaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InscricaoVagaRepository extends JpaRepository<InscricaoVaga, Long> {
    boolean existsByCandidatoIdAndVagaId(Long candidatoId, Long vagaId);

    Optional<InscricaoVaga> findByCandidatoIdAndVagaId(Long candidatoId, Long vagaId);
}
