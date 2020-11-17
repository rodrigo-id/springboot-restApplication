package com.example.springboot.restservice.restApiExample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2")
public class EjemploVersionamiento2 {

    @GetMapping("/mensaje")
    public String mensajeVersion(){
        return "Soy endpoint de la version 2";
    }
}

