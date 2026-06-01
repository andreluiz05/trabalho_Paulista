package com.example.trabalha_paulista.controllers;

import com.example.trabalha_paulista.dtos.AuthResponse;
import com.example.trabalha_paulista.dtos.LoginRequest;
import com.example.trabalha_paulista.dtos.UsuarioCreateRequest;
import com.example.trabalha_paulista.dtos.UsuarioResponse;
import com.example.trabalha_paulista.dtos.UsuarioUpdateRequest;
import com.example.trabalha_paulista.exceptions.ResourceNotFoundException;
import com.example.trabalha_paulista.models.Usuario;
import com.example.trabalha_paulista.repository.UsuarioRepository;
import com.example.trabalha_paulista.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthController(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody UsuarioCreateRequest request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setTelefone(request.telefone());
        usuario.setTipoUsuario(request.tipoUsuario());

        Usuario salvo = usuarioRepository.save(usuario);
        UserDetails userDetails = userDetailsService.loadUserByUsername(salvo.getEmail());
        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, "Bearer", UsuarioResponse.from(salvo));
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.senha()));
        Usuario usuario = usuarioRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais invalidas"));
        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());
        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token, "Bearer", UsuarioResponse.from(usuario));
    }

    @GetMapping("/usuarios")
    public List<UsuarioResponse> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(UsuarioResponse::from).toList();
    }

    @GetMapping("/usuarios/{id}")
    public UsuarioResponse buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(UsuarioResponse::from)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));
    }

    @PostMapping("/usuarios")
    public UsuarioResponse criarUsuario(@Valid @RequestBody UsuarioCreateRequest request) {
        if (usuarioRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Email ja cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(passwordEncoder.encode(request.senha()));
        usuario.setTelefone(request.telefone());
        usuario.setTipoUsuario(request.tipoUsuario());

        return UsuarioResponse.from(usuarioRepository.save(usuario));
    }

    @PutMapping("/usuarios/{id}")
    public UsuarioResponse atualizarUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));

        usuarioRepository.findByEmail(request.email())
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

        return UsuarioResponse.from(usuarioRepository.save(usuario));
    }

    @DeleteMapping("/usuarios/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario nao encontrado"));
        usuarioRepository.delete(usuario);
    }
}
