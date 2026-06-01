package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.InscricaoVagaRequest;
import com.example.trabalha_paulista.dtos.InscricaoVagaResponse;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.InscricaoVaga;
import com.example.trabalha_paulista.models.Usuario;
import com.example.trabalha_paulista.models.Vaga;
import com.example.trabalha_paulista.repository.InscricaoVagaRepository;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/inscricoes-vagas")
public class InscricaoVagaController {

    private final InscricaoVagaRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final VagaRepository vagaRepository;

    public InscricaoVagaController(InscricaoVagaRepository repository, UsuarioRepository usuarioRepository,
            VagaRepository vagaRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.vagaRepository = vagaRepository;
    }

    @GetMapping
    public List<InscricaoVagaResponse> listar() {
        return repository.findAll().stream().map(InscricaoVagaResponse::from).toList();
    }

    @GetMapping("/{id}")
    public InscricaoVagaResponse buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(InscricaoVagaResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Inscricao em vaga nao encontrada"));
    }

    @PostMapping
    public InscricaoVagaResponse criar(@Valid @RequestBody InscricaoVagaRequest request) {
        if (repository.existsByCandidatoIdAndVagaId(request.candidatoId(), request.vagaId())) {
            throw new IllegalArgumentException("Usuario ja inscrito nesta vaga");
        }

        InscricaoVaga inscricao = new InscricaoVaga();
        inscricao.setCandidato(buscarUsuario(request.candidatoId()));
        inscricao.setVaga(buscarVaga(request.vagaId()));
        inscricao.setDataInscricao(LocalDate.now());
        return InscricaoVagaResponse.from(repository.save(inscricao));
    }

    @PutMapping("/{id}")
    public InscricaoVagaResponse atualizar(@PathVariable Long id, @Valid @RequestBody InscricaoVagaRequest request) {
        InscricaoVaga inscricao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscricao em vaga nao encontrada"));

        repository.findByCandidatoIdAndVagaId(request.candidatoId(), request.vagaId())
                .filter(existente -> !existente.getId().equals(id))
                .ifPresent(existente -> {
                    throw new IllegalArgumentException("Usuario ja inscrito nesta vaga");
                });

        inscricao.setCandidato(buscarUsuario(request.candidatoId()));
        inscricao.setVaga(buscarVaga(request.vagaId()));
        return InscricaoVagaResponse.from(repository.save(inscricao));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        InscricaoVaga inscricao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscricao em vaga nao encontrada"));
        repository.delete(inscricao);
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidato nao encontrado"));
    }

    private Vaga buscarVaga(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vaga nao encontrada"));
    }
}
