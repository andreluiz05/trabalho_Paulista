package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Usuario;
import com.example.trabalha_paulista.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // GET - Listar todos (Read)
    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // POST - Criar novo (Create)
    @PostMapping
    public Usuario criar(@Valid @RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    // PUT - Atualizar (Update)
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @Valid @RequestBody Usuario dadosNovos) {
        return repository.findById(id).map(usuario -> {
            usuario.setNome(dadosNovos.getNome());
            usuario.setEmail(dadosNovos.getEmail());
            usuario.setSenha(dadosNovos.getSenha());
            return repository.save(usuario);
        }).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
    }

    // DELETE - Remover (Delete)
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        repository.delete(usuario);
    }
}