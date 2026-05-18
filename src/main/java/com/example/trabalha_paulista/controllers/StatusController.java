package com.example.trabalha_paulista.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/status")
    public String checarStatus() {
        return "Tudo OK! A API do Trabalha Paulista está rodando perfeitamente. 🚀";
    }
}