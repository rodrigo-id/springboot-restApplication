package com.example.springboot.restservice.restApiExample.controller;

import com.example.springboot.restservice.restApiExample.configuration.BasicConfiguration;
import com.example.springboot.restservice.restApiExample.model.Saludo;
import com.example.springboot.restservice.restApiExample.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WelcomeController {

    private WelcomeService welcomeService;
    private BasicConfiguration basicConfiguration;

    @Autowired
    public void setWelcomeService(WelcomeService welcomeService){
        this.welcomeService = welcomeService;
    }

    @Autowired
    public void setBasicConfiguration(BasicConfiguration basicConfiguration){
        this.basicConfiguration = basicConfiguration;
    }

    @GetMapping("/welcome")
    public Saludo welcome(){
        return welcomeService.retrieveWelcomeMessage();
    }

    @GetMapping("/welcome-from-file")
    public String welcomeFromPropertiesFile(){
        return welcomeService.retrieveWelcomeMessageFromPropertiesFile();
    }

    @GetMapping("/dynamic-configuration")
    public Map dynamicConfiguration(){
        Map map = new HashMap();
        map.put("message", basicConfiguration.getMessage());
        map.put("number", basicConfiguration.getNumber());
        map.put("value", basicConfiguration.isValue());
        return map;
    }

}
