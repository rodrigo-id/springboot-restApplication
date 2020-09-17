package com.example.springboot.restservice.restApiExample.service;

import com.example.springboot.restservice.restApiExample.model.Saludo;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    private Saludo saludo;

    public Saludo retrieveWelcomeMessage(){
        this.saludo = new Saludo();
        saludo.setSaludo("soy un mensaje de bienvenida para loococra");
        return saludo;
    }
}
