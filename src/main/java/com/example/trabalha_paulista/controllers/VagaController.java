package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.models.Vaga;
import com.example.trabalha_paulista.repository.VagaRepository;
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
    public Vaga criar(@RequestBody Vaga vaga) {
        return repository.save(vaga);
    }

    @PutMapping("/{id}")
    public Vaga atualizar(@PathVariable Long id, @RequestBody Vaga dadosNovos) {
        return repository.findById(id).map(vaga -> {
            vaga.setTitulo(dadosNovos.getTitulo());
            vaga.setDescricao(dadosNovos.getDescricao());
            vaga.setEmpresa(dadosNovos.getEmpresa());
            vaga.setCidade(dadosNovos.getCidade());
            vaga.setTipo_vaga(dadosNovos.getTipo_vaga());
            return repository.save(vaga);
        }).orElseThrow(() -> new RuntimeException("Vaga não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}