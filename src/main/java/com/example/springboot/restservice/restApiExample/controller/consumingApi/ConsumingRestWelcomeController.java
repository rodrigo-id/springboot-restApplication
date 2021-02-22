package com.example.springboot.restservice.restApiExample.controller.consumingApi;

import com.example.springboot.restservice.restApiExample.model.Saludo;
import com.example.springboot.restservice.restApiExample.service.consumingApi.ConsumingRestWelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ConsumingRestWelcomeController {
    private ConsumingRestWelcomeService restWelcomeService;

    @Autowired
    private void setRestWelcomeService(ConsumingRestWelcomeService restWelcomeService){
        this.restWelcomeService = restWelcomeService;
    }

    /*
    @GetMapping("/getForObjectWelcome")
    public Saludo getForObjectWelcome(){
        String path = "/welcome";
        Saludo response = restTemplate.getForObject(endpoint+path, Saludo.class);
        return response;
    }
     */

    @GetMapping("/hello")
    public String hello(){
        return "Heellooo!!";
    }

    @GetMapping("/getForObjectWelcome")
    public Saludo getForObjectWelcome(){
        return this.restWelcomeService.getWelcomeMessage();
    }

}
