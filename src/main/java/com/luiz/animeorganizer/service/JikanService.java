package com.luiz.animeorganizer.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JikanService {
    private final RestTemplate restTemplate;

    public JikanService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String buscarAnimePorTitulo(String nome) {
        String url = "https://api.jikan.moe/v4/anime?q=" + nome;
        return restTemplate.getForObject(url, String.class);
    }
}
