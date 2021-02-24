package com.example.springboot.restservice.restApiExample.controller;

import com.example.springboot.restservice.restApiExample.RestApiExampleApplication;
import com.example.springboot.restservice.restApiExample.model.Question;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RestApiExampleApplication.class)
public class SurveyControllerIntegrationTest {

    //Para asignar el puerto que de forma aleatorea se ha escogido
    @LocalServerPort
    private int port;
    @Value("${app.host}")
    private String host;

    TestRestTemplate restTemplate;
    HttpHeaders headers;

    @BeforeEach
    public void before(){
        this.restTemplate = new TestRestTemplate();
        this.headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testRetrieveASpecificQuestionForASurvey(){
        String url = host+port+"/surveys/Survey1/questions/Question1";
        //String output = restTemplate.getForObject(url, String.class);

        HttpEntity entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Response : "+response.getBody());

        Assertions.assertTrue(response.getBody().contains("\"id\":\"Question1\""));

    }

    @Test
    public void testRetrieveANonExistenceQuestionForASurvey(){
        String url = host+port+"/surveys/Survey1/questions/Question11";

        HttpEntity entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Response : "+response.getBody());

        Assertions.assertFalse(response.getBody().contains("\"id\":\"Question11\""));

    }


    @Test
    public void testRetrieveAllQuestionsOfASurvey() {
        String url = host+port+"/surveys/Survey1/questions";

        HttpEntity entity = new HttpEntity<String>(null, headers);
        ResponseEntity<List<Question>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Question>>() {
                });
        System.out.println("Response : "+response.getBody());
        Question questionResponse = new Question("Question1", "Largest Country in the World", "Russia", Arrays.asList("India", "Russia", "United States", "China"));

        Assertions.assertTrue(response.getBody().contains(questionResponse));

    }

    @Test
    public void testAddQuestionsToASurvey(){
        String url = host+port+"/surveys/Survey1/questions";

        List<Question> newQuestions = new ArrayList<>();
        newQuestions.add(new Question("Question4", "soy la novedad", "chile", Arrays.asList("India", "Russia", "United States", "China")));
        newQuestions.add(new Question("Question5", "soy la novedad", "chile", Arrays.asList("India", "Russia", "United States", "China")));

        //convert the Questions object into json object
        HttpEntity entity = new HttpEntity<>(newQuestions, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                String.class);
        System.out.println("Location: "+response.getBody());
        Assertions.assertTrue(response.getBody().contains("/surveys/Survey1/questions"));

    }


}
