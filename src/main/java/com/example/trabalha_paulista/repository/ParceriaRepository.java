package com.example.trabalha_paulista.repository;
import com.example.trabalha_paulista.models.Parceria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParceriaRepository extends JpaRepository<Parceria, Long> {}