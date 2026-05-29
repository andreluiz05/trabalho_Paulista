package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.UsuarioCreateRequest;
import com.example.trabalha_paulista.dtos.UsuarioResponse;
import com.example.trabalha_paulista.dtos.UsuarioUpdateRequest;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Usuario;
import com.example.trabalha_paulista.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public List<UsuarioResponse> listar() {
        return repository.findAll().stream().map(UsuarioResponse::from).toList();
    }

    @PostMapping
    public UsuarioResponse criar(@Valid @RequestBody UsuarioCreateRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setTelefone(request.telefone());
        usuario.setTipoUsuario(request.tipoUsuario());

        return UsuarioResponse.from(repository.save(usuario));
    }

    @PutMapping("/{id}")
    public UsuarioResponse atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateRequest request) {
        return repository.findById(id).map(usuario -> {
            repository.findByEmail(request.email())
                    .filter(existente -> !existente.getId().equals(id))
                    .ifPresent(existente -> {
                        throw new IllegalArgumentException("Email ja cadastrado");
                    });

            usuario.setNome(request.nome());
            usuario.setEmail(request.email());
            if (request.senha() != null && !request.senha().isBlank()) {
                usuario.setSenha(passwordEncoder.encode(request.senha()));
            }
            usuario.setTelefone(request.telefone());
            usuario.setTipoUsuario(request.tipoUsuario());
            return UsuarioResponse.from(repository.save(usuario));
        }).orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));
        repository.delete(usuario);
    }
}
