package com.example.springboot.restservice.restApiExample.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.springboot.restservice.restApiExample.RestApiExampleApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RestApiExampleApplication.class)
public class SurveyControllerIT {

    //Para asignar el puerto que de forma aleatorea se ha escogido
    @LocalServerPort
    private int port;

    @Test
    public void test(){
        System.out.print("El puerto es "+port);
        assertFalse(false, "No implementado aun");
    }
}
