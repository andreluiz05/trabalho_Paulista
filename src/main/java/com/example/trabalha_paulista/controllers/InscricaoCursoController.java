package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.InscricaoCursoRequest;
import com.example.trabalha_paulista.dtos.InscricaoCursoResponse;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Curso;
import com.example.trabalha_paulista.models.InscricaoCurso;
import com.example.trabalha_paulista.models.Usuario;
import com.example.trabalha_paulista.repository.CursoRepository;
import com.example.trabalha_paulista.repository.InscricaoCursoRepository;
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

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/inscricoes-cursos")
public class InscricaoCursoController {

    private final InscricaoCursoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;

    public InscricaoCursoController(InscricaoCursoRepository repository, UsuarioRepository usuarioRepository,
            CursoRepository cursoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping
    public List<InscricaoCursoResponse> listar() {
        return repository.findAll().stream().map(InscricaoCursoResponse::from).toList();
    }

    @GetMapping("/{id}")
    public InscricaoCursoResponse buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(InscricaoCursoResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Inscricao em curso nao encontrada"));
    }

    @PostMapping
    public InscricaoCursoResponse criar(@Valid @RequestBody InscricaoCursoRequest request) {
        if (repository.existsByAlunoIdAndCursoId(request.alunoId(), request.cursoId())) {
            throw new IllegalArgumentException("Usuario ja inscrito neste curso");
        }

        InscricaoCurso inscricao = new InscricaoCurso();
        inscricao.setAluno(buscarUsuario(request.alunoId()));
        inscricao.setCurso(buscarCurso(request.cursoId()));
        inscricao.setDataInscricao(LocalDate.now());
        return InscricaoCursoResponse.from(repository.save(inscricao));
    }

    @PutMapping("/{id}")
    public InscricaoCursoResponse atualizar(@PathVariable Long id, @Valid @RequestBody InscricaoCursoRequest request) {
        InscricaoCurso inscricao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscricao em curso nao encontrada"));

        repository.findByAlunoIdAndCursoId(request.alunoId(), request.cursoId())
                .filter(existente -> !existente.getId().equals(id))
                .ifPresent(existente -> {
                    throw new IllegalArgumentException("Usuario ja inscrito neste curso");
                });

        inscricao.setAluno(buscarUsuario(request.alunoId()));
        inscricao.setCurso(buscarCurso(request.cursoId()));
        return InscricaoCursoResponse.from(repository.save(inscricao));
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        InscricaoCurso inscricao = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscricao em curso nao encontrada"));
        repository.delete(inscricao);
    }

    private Usuario buscarUsuario(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno nao encontrado"));
    }

    private Curso buscarCurso(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso nao encontrado"));
    }
}
