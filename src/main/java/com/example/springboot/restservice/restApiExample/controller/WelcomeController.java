package com.example.springboot.restservice.restApiExample.controller;

import com.example.springboot.restservice.restApiExample.model.Saludo;
import com.example.springboot.restservice.restApiExample.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    private WelcomeService welcomeService;

    @Autowired
    public void setWelcomeService(WelcomeService welcomeService){
        this.welcomeService = welcomeService;
    }

    @GetMapping("/welcome")
    public Saludo welcome(){
        return welcomeService.retrieveWelcomeMessage();
    }
}
