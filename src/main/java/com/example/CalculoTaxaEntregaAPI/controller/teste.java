package com.example.CalculoTaxaEntregaAPI.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class teste {

    @GetMapping("/saudacao")
    public String saudacao() {
        return "Olá, Spring Boot!";
    }
}
