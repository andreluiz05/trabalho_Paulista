package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.InscricaoMentoriaRequest;
import com.example.trabalha_paulista.dtos.InscricaoMentoriaResponse;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.InscricaoMentoria;
import com.example.trabalha_paulista.models.Mentoria;
import com.example.trabalha_paulista.models.Usuario;
import com.example.trabalha_paulista.repository.InscricaoMentoriaRepository;
import com.example.trabalha_paulista.repository.MentoriaRepository;
import com.example.trabalha_paulista.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/inscricoes-mentorias")
public class InscricaoMentoriaController {

    private final InscricaoMentoriaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final MentoriaRepository mentoriaRepository;

    public InscricaoMentoriaController(InscricaoMentoriaRepository repository, UsuarioRepository usuarioRepository, MentoriaRepository mentoriaRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mentoriaRepository = mentoriaRepository;
    }

    @GetMapping
    public List<InscricaoMentoriaResponse> listar() {
        return repository.findAll().stream().map(InscricaoMentoriaResponse::from).toList();
    }

    @PostMapping
    public InscricaoMentoriaResponse criar(@Valid @RequestBody InscricaoMentoriaRequest request) {
        if (repository.existsByParticipanteIdAndMentoriaId(request.participanteId(), request.mentoriaId())) {
            throw new IllegalArgumentException("Usuario ja inscrito nesta mentoria");
        }

        InscricaoMentoria inscricao = new InscricaoMentoria();
        inscricao.setParticipante(buscarUsuario(request.participanteId()));
        inscricao.setMentoria(buscarMentoria(request.mentoriaId()));
        inscricao.setDataInscricao(LocalDate.now());
        return InscricaoMentoriaResponse.from(repository.save(inscricao));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        InscricaoMentoria inscricao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscricao em mentoria nao encontrada"));
        repository.delete(inscricao);
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Participante nao encontrado"));
    }

    private Mentoria buscarMentoria(Long id) {
        return mentoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mentoria nao encontrada"));
    }
}
