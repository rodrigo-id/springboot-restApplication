package com.example.springboot.restservice.restApiExample.controller;

import com.example.springboot.restservice.restApiExample.model.Question;
import com.example.springboot.restservice.restApiExample.model.Survey;
import com.example.springboot.restservice.restApiExample.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class SurveyController {
    private SurveyService surveyService;

    @Autowired
    public void setSurveyService(SurveyService surveyService){
        this.surveyService = surveyService;
    }

    @GetMapping("/surveys")
    public List<Survey> getAllSurveys(HttpServletRequest request){

        return surveyService.retriveAllSurveys();
    }

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> retrieveAllQuestionsOfASurvey(@PathVariable String surveyId){
        return surveyService.retrieveQuestions(surveyId);
    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question retrieveASpecificQuestionForASurvey(@PathVariable String surveyId, @PathVariable String questionId){
        return surveyService.retrieveQuestion(surveyId, questionId);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public List<ResponseEntity<?>> addQuestionsToASurvey(@PathVariable String surveyId, @RequestBody List<Question> newQuestions){
        List<Question> questions = surveyService.addQuestionsToSurvey(surveyId, newQuestions);
        if(questions == null){
            return Collections.singletonList(ResponseEntity.noContent().build());
        }
        /**
         *  - Segun el standard de aplicaciones REST al crear un recurso este nuevo recurso debe ser
         * devuelto mediante la URI en la respuesta de la cabecera
         * - tambien devolver HttpStatus.CREATED para notificar la correcta creacion
         */
        List<ResponseEntity<?>> responseEntities = new ArrayList<>();
        questions.forEach(q -> {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(q.getId()).toUri();
            responseEntities.add(ResponseEntity.created(location).build());
        });
        return responseEntities;
    }


}
