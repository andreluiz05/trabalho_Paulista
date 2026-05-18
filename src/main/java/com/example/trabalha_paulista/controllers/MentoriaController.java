package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.models.Mentoria;
import com.example.trabalha_paulista.repository.MentoriaRepository;
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
    public Mentoria criar(@RequestBody Mentoria mentoria) {
        return repository.save(mentoria);
    }

    @PutMapping("/{id}")
    public Mentoria atualizar(@PathVariable Long id, @RequestBody Mentoria dadosNovos) {
        return repository.findById(id).map(mentoria -> {
            mentoria.setTema(dadosNovos.getTema());
            mentoria.setDescricao(dadosNovos.getDescricao());
            mentoria.setData(dadosNovos.getData());
            mentoria.setMentor(dadosNovos.getMentor());
            return repository.save(mentoria);
        }).orElseThrow(() -> new RuntimeException("Mentoria não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}