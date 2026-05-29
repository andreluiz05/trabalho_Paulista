package com.example.trabalha_paulista.repository;

import com.example.trabalha_paulista.models.InscricaoMentoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoMentoriaRepository extends JpaRepository<InscricaoMentoria, Long> {
    boolean existsByParticipanteIdAndMentoriaId(Long participanteId, Long mentoriaId);
}
