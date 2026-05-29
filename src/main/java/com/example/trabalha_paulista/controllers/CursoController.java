package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.CursoRequest;
import com.example.trabalha_paulista.dtos.CursoResponse;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Curso;
import com.example.trabalha_paulista.repository.CursoRepository;
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
@RequestMapping("/cursos")
public class CursoController {

    private final CursoRepository repository;

    public CursoController(CursoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<CursoResponse> listar() {
        return repository.findAll().stream().map(CursoResponse::from).toList();
    }

    @PostMapping
    public CursoResponse criar(@Valid @RequestBody CursoRequest request) {
        Curso curso = new Curso();
        preencher(curso, request);
        return CursoResponse.from(repository.save(curso));
    }

    @PutMapping("/{id}")
    public CursoResponse atualizar(@PathVariable Long id, @Valid @RequestBody CursoRequest request) {
        return repository.findById(id).map(curso -> {
            preencher(curso, request);
            return CursoResponse.from(repository.save(curso));
        }).orElseThrow(() -> new ResourceNotFoundException("Curso nao encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Curso curso = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso nao encontrado"));
        repository.delete(curso);
    }

    private void preencher(Curso curso, CursoRequest request) {
        curso.setNome(request.nome());
        curso.setDescricao(request.descricao());
        curso.setInstituicao(request.instituicao());
        curso.setCargaHoraria(request.cargaHoraria());
    }
}
