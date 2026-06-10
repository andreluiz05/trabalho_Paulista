package com.example.trabalha_paulista.repository;

import com.example.trabalha_paulista.models.InscricaoMentoria;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface InscricaoMentoriaRepository extends JpaRepository<InscricaoMentoria, Long> {
    boolean existsByParticipanteIdAndMentoriaId(Long participanteId, Long mentoriaId);

    Optional<InscricaoMentoria> findByParticipanteIdAndMentoriaId(Long participanteId, Long mentoriaId);
}
