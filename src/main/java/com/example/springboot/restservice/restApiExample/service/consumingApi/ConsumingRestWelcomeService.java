package com.example.springboot.restservice.restApiExample.service.consumingApi;

import com.example.springboot.restservice.restApiExample.model.Saludo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumingRestWelcomeService {
    private final RestTemplate restTemplate;

    @Value("${domain}")
    private String endpoint;

    @Autowired
    public ConsumingRestWelcomeService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public Saludo getWelcomeMessage(){
        return this.restTemplate.getForObject(endpoint+"/welcome", Saludo.class);
    }

}
