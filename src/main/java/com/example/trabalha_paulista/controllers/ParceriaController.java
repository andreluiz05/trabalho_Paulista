package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.models.Parceria;
import com.example.trabalha_paulista.repository.ParceriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/parcerias")
public class ParceriaController {

    @Autowired
    private ParceriaRepository repository;

    @GetMapping
    public List<Parceria> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Parceria criar(@RequestBody Parceria parceria) {
        return repository.save(parceria);
    }

    @PutMapping("/{id}")
    public Parceria atualizar(@PathVariable Long id, @RequestBody Parceria dadosNovos) {
        return repository.findById(id).map(parceria -> {
            // 🎯 Correção cirúrgica dos métodos aqui:
            parceria.setNome_empresa(dadosNovos.getNome_empresa());
            parceria.setDescricao(dadosNovos.getDescricao());
            parceria.setContato(dadosNovos.getContato());
            return repository.save(parceria);
        }).orElseThrow(() -> new RuntimeException("Parceria não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}