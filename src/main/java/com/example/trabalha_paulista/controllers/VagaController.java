package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.VagaRequest;
import com.example.trabalha_paulista.dtos.VagaResponse;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Usuario;
import com.example.trabalha_paulista.models.Vaga;
import com.example.trabalha_paulista.repository.UsuarioRepository;
import com.example.trabalha_paulista.repository.VagaRepository;
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
@RequestMapping("/vagas")
public class VagaController {

    private final VagaRepository repository;
    private final UsuarioRepository usuarioRepository;

    public VagaController(VagaRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<VagaResponse> listar() {
        return repository.findAll().stream().map(VagaResponse::from).toList();
    }

    @PostMapping
    public VagaResponse criar(@Valid @RequestBody VagaRequest request) {
        Vaga vaga = new Vaga();
        preencher(vaga, request);
        return VagaResponse.from(repository.save(vaga));
    }

    @PutMapping("/{id}")
    public VagaResponse atualizar(@PathVariable Long id, @Valid @RequestBody VagaRequest request) {
        return repository.findById(id).map(vaga -> {
            preencher(vaga, request);
            return VagaResponse.from(repository.save(vaga));
        }).orElseThrow(() -> new ResourceNotFoundException("Vaga nao encontrada"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Vaga vaga = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga nao encontrada"));
        repository.delete(vaga);
    }

    private void preencher(Vaga vaga, VagaRequest request) {
        vaga.setTitulo(request.titulo());
        vaga.setDescricao(request.descricao());
        vaga.setEmpresa(request.empresa());
        vaga.setCidade(request.cidade());
        vaga.setTipoVaga(request.tipoVaga());
        vaga.setPublicador(buscarPublicador(request.publicadorId()));
    }

    private Usuario buscarPublicador(Long publicadorId) {
        if (publicadorId == null) {
            return null;
        }
        return usuarioRepository.findById(publicadorId)
                .orElseThrow(() -> new ResourceNotFoundException("Publicador nao encontrado"));
    }
}
