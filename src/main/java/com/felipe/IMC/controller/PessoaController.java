package com.felipe.IMC.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PessoaController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

}
