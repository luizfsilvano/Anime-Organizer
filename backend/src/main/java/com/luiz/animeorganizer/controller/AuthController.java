package com.luiz.animeorganizer.controller;

import com.luiz.animeorganizer.entity.Usuario;
import com.luiz.animeorganizer.repository.UsuarioRepository;
import com.luiz.animeorganizer.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder =  passwordEncoder;
    }

    @PostMapping("/auth/register")
    public Usuario register(@RequestBody Usuario usuario)
    {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @PostMapping("/auth/login")
    public String login(@RequestBody Usuario usuario)
    {
        Usuario user = usuarioRepository
                .findByEmail(usuario.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(usuario.getSenha(), user.getSenha())) {
                throw new RuntimeException("Senha errada");
        }

        return JwtService.getToken(user.getEmail());
    }

}
