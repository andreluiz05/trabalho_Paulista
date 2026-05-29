package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Vaga;
import com.example.trabalha_paulista.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    @Autowired
    private VagaRepository repository;

    @GetMapping
    public List<Vaga> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Vaga criar(@Valid @RequestBody Vaga vaga) {
        return repository.save(vaga);
    }

    @PutMapping("/{id}")
    public Vaga atualizar(@PathVariable Long id, @Valid @RequestBody Vaga dadosNovos) {
        return repository.findById(id).map(vaga -> {
            vaga.setTitulo(dadosNovos.getTitulo());
            vaga.setDescricao(dadosNovos.getDescricao());
            vaga.setEmpresa(dadosNovos.getEmpresa());
            vaga.setCidade(dadosNovos.getCidade());
            vaga.setTipo_vaga(dadosNovos.getTipo_vaga());
            return repository.save(vaga);
        }).orElseThrow(() -> new ResourceNotFoundException("Vaga não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Vaga vaga = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga não encontrada"));
        repository.delete(vaga);
    }
}