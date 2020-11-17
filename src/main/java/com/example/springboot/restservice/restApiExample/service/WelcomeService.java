package com.example.springboot.restservice.restApiExample.service;

import com.example.springboot.restservice.restApiExample.model.Saludo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WelcomeService {
    private Saludo saludo;

    //annotation to read a properties from application.properties file
    @Value("${app.description}")
    private String messageFromPropertiesFile;

    public Saludo retrieveWelcomeMessage(){
        this.saludo = new Saludo();
        saludo.setSaludo("soy un mensaje de bienvenida para loococra");
        return saludo;
    }

    public String retrieveWelcomeMessageFromPropertiesFile(){
        return messageFromPropertiesFile;
    }
}
