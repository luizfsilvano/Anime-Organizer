package com.luiz.animeorganizer.controller;

import com.luiz.animeorganizer.service.JikanService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/animes")
public class AnimeController {
    private final JikanService jikanService;

    public AnimeController (JikanService jikanService) {
        this.jikanService = jikanService;
    }

    @GetMapping("/search")
    public String search(@RequestParam String nome) {
        return jikanService.buscarAnimePorTitulo(nome);
    }
}
