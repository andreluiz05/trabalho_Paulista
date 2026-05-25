package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Curso;
import com.example.trabalha_paulista.repository.CursoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @GetMapping
    public List<Curso> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Curso criar(@Valid @RequestBody Curso curso) {
        return repository.save(curso);
    }

    @PutMapping("/{id}")
    public Curso atualizar(@PathVariable Long id, @Valid @RequestBody Curso dadosNovos) {
        return repository.findById(id).map(curso -> {
            curso.setNome(dadosNovos.getNome());
            curso.setDescricao(dadosNovos.getDescricao());
            curso.setInstituicao(dadosNovos.getInstituicao());
            curso.setCarga_horaria(dadosNovos.getCarga_horaria());
            return repository.save(curso);
        }).orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));
        repository.delete(curso);
    }
}