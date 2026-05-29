package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.ParceriaRequest;
import com.example.trabalha_paulista.dtos.ParceriaResponse;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Parceria;
import com.example.trabalha_paulista.repository.ParceriaRepository;
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
@RequestMapping("/parcerias")
public class ParceriaController {

    private final ParceriaRepository repository;

    public ParceriaController(ParceriaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<ParceriaResponse> listar() {
        return repository.findAll().stream().map(ParceriaResponse::from).toList();
    }

    @PostMapping
    public ParceriaResponse criar(@Valid @RequestBody ParceriaRequest request) {
        Parceria parceria = new Parceria();
        preencher(parceria, request);
        return ParceriaResponse.from(repository.save(parceria));
    }

    @PutMapping("/{id}")
    public ParceriaResponse atualizar(@PathVariable Long id, @Valid @RequestBody ParceriaRequest request) {
        return repository.findById(id).map(parceria -> {
            preencher(parceria, request);
            return ParceriaResponse.from(repository.save(parceria));
        }).orElseThrow(() -> new ResourceNotFoundException("Parceria nao encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Parceria parceria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parceria nao encontrada"));
        repository.delete(parceria);
    }

    private void preencher(Parceria parceria, ParceriaRequest request) {
        parceria.setNomeEmpresa(request.nomeEmpresa());
        parceria.setDescricao(request.descricao());
        parceria.setContato(request.contato());
    }
}
