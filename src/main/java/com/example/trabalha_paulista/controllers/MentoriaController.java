package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Mentoria;
import com.example.trabalha_paulista.repository.MentoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/mentorias")
public class MentoriaController {

    @Autowired
    private MentoriaRepository repository;

    @GetMapping
    public List<Mentoria> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Mentoria criar(@Valid @RequestBody Mentoria mentoria) {
        return repository.save(mentoria);
    }

    @PutMapping("/{id}")
    public Mentoria atualizar(@PathVariable Long id, @Valid @RequestBody Mentoria dadosNovos) {
        return repository.findById(id).map(mentoria -> {
            mentoria.setTema(dadosNovos.getTema());
            mentoria.setDescricao(dadosNovos.getDescricao());
            mentoria.setData(dadosNovos.getData());
            mentoria.setMentor(dadosNovos.getMentor());
            return repository.save(mentoria);
        }).orElseThrow(() -> new ResourceNotFoundException("Mentoria não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Mentoria mentoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mentoria não encontrada"));
        repository.delete(mentoria);
    }
}