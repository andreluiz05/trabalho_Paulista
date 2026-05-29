package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.MentoriaRequest;
import com.example.trabalha_paulista.dtos.MentoriaResponse;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Mentoria;
import com.example.trabalha_paulista.repository.MentoriaRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mentorias")
public class MentoriaController {

    private final MentoriaRepository repository;

    public MentoriaController(MentoriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<MentoriaResponse> listar() {
        return repository.findAll().stream().map(MentoriaResponse::from).toList();
    }

    @PostMapping
    public MentoriaResponse criar(@Valid @RequestBody MentoriaRequest request) {
        Mentoria mentoria = new Mentoria();
        preencher(mentoria, request);
        return MentoriaResponse.from(repository.save(mentoria));
    }

    @PutMapping("/{id}")
    public MentoriaResponse atualizar(@PathVariable Long id, @Valid @RequestBody MentoriaRequest request) {
        return repository.findById(id).map(mentoria -> {
            preencher(mentoria, request);
            return MentoriaResponse.from(repository.save(mentoria));
        }).orElseThrow(() -> new ResourceNotFoundException("Mentoria nao encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Mentoria mentoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mentoria nao encontrada"));
        repository.delete(mentoria);
    }

    private void preencher(Mentoria mentoria, MentoriaRequest request) {
        mentoria.setTema(request.tema());
        mentoria.setDescricao(request.descricao());
        mentoria.setData(request.data());
        mentoria.setMentor(request.mentor());
    }
}
