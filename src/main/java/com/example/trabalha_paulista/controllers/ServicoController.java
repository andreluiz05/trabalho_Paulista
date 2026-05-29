package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.ServicoRequest;
import com.example.trabalha_paulista.dtos.ServicoResponse;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Servico;
import com.example.trabalha_paulista.models.Usuario;
import com.example.trabalha_paulista.repository.ServicoRepository;
import com.example.trabalha_paulista.repository.UsuarioRepository;
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
@RequestMapping("/servicos")
public class ServicoController {

    private final ServicoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public ServicoController(ServicoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<ServicoResponse> listar() {
        return repository.findAll().stream().map(ServicoResponse::from).toList();
    }

    @PostMapping
    public ServicoResponse criar(@Valid @RequestBody ServicoRequest request) {
        Servico servico = new Servico();
        preencher(servico, request);
        return ServicoResponse.from(repository.save(servico));
    }

    @PutMapping("/{id}")
    public ServicoResponse atualizar(@PathVariable Long id, @Valid @RequestBody ServicoRequest request) {
        return repository.findById(id).map(servico -> {
            preencher(servico, request);
            return ServicoResponse.from(repository.save(servico));
        }).orElseThrow(() -> new ResourceNotFoundException("Servico nao encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Servico servico = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servico nao encontrado"));
        repository.delete(servico);
    }

    private void preencher(Servico servico, ServicoRequest request) {
        servico.setNome(request.nome());
        servico.setDescricao(request.descricao());
        servico.setPreco(request.preco());
        servico.setCategoria(request.categoria());
        servico.setAnunciante(buscarUsuario(request.anuncianteId()));
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Anunciante nao encontrado"));
    }
}
