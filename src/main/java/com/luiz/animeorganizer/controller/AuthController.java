package com.luiz.animeorganizer.controller;

import com.luiz.animeorganizer.entity.Usuario;
import com.luiz.animeorganizer.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final UsuarioRepository usuarioRepository;

    public AuthController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("auth/register")
    public Usuario register(@RequestBody Usuario usuario)
    {
        return usuarioRepository.save(usuario);
    }

    @PostMapping("auth/login")
    public String login(@RequestBody Usuario usuario)
    {
        Usuario user = usuarioRepository
                .findByEmail(usuario.getEmail())
                .orElseThrow();

        if (!user.getSenha().equals(usuario.getSenha())) {
            throw new RuntimeException("Senha errada");
        }

        return "Login Ok (fake)";
    }

}
