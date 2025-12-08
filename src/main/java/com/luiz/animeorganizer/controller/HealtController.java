package com.luiz.animeorganizer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealtController {
    @GetMapping("/health")
    public String health() {
        return "A API tá rodando fio";
    }
}
