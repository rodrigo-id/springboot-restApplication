package com.example.springboot.restservice.restApiExample.service;

import com.example.springboot.restservice.restApiExample.model.Question;
import com.example.springboot.restservice.restApiExample.model.Survey;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;


public class SurveyServiceTest {

    @Test
    public void whenFindASurveyByIdThenRetrieveASurvey(){
        SurveyService surveyService = new SurveyService();
        Question question1 = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                "India", "Russia", "United States", "China"));
        Question question2 = new Question("Question2",
                "Most Populus Country in the World", "China", Arrays.asList(
                "India", "Russia", "United States", "China"));
        Survey survey = new Survey("Survey1", "titulo", "descripcion", Arrays.asList(question1, question2));
        Assertions.assertEquals(survey, surveyService.retrieveSurvey("Survey1"));
    }
}