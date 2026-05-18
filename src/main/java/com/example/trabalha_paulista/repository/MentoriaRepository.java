package com.example.trabalha_paulista.repository;
import com.example.trabalha_paulista.models.Mentoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentoriaRepository extends JpaRepository<Mentoria, Long> {}