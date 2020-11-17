package com.example.springboot.restservice.restApiExample.service;

import com.example.springboot.restservice.restApiExample.model.Question;
import com.example.springboot.restservice.restApiExample.model.Survey;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class SurveyService {
    private static List<Survey> surveys = new ArrayList<>();
    static {
        Question question1 = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                "India", "Russia", "United States", "China"));
        Question question2 = new Question("Question2",
                "Most Populus Country in the World", "China", Arrays.asList(
                "India", "Russia", "United States", "China"));
        Question question3 = new Question("Question3",
                "Highest GDP in the World", "United States", Arrays.asList(
                "India", "Russia", "United States", "China"));
        Question question4 = new Question("Question4",
                "Second largest english speaking country", "India", Arrays
                .asList("India", "Russia", "United States", "China"));

        List<Question> questions = new ArrayList<>(Arrays.asList(question1,
                question2, question3, question4));

        Survey survey = new Survey("Survey1", "My Favorite Survey",
                "Description of the Survey", questions);

        surveys.add(survey);
    }

    public List<Survey> retriveAllSurveys() {
        return surveys;
    }

    public Survey retrieveSurvey(String id){
        return surveys.stream()
                .filter(survey -> survey.getId().equals(id))
                .findFirst().orElse(null);
    }

    public List<Question> retrieveQuestions(String surveyId){
        return Objects.requireNonNull(
                surveys.stream()
                        .filter(survey -> survey.getId().equals(surveyId))
                        .findFirst().orElse(null))
                .getQuestions();
    }

    public Question retrieveQuestion(String surveyId, String questionId){
        return Objects.requireNonNull(retrieveQuestions(surveyId)
                .stream()
                .filter(question -> question.getId().equals(questionId))
                .findFirst().orElse(null));
    }

    public boolean addSurvey(Survey survey){
        return surveys.add(survey);
    }

    public List<Question> addQuestionsToSurvey(String idSurvey, List<Question> newQuestions){
        boolean isAdd = Objects.requireNonNull(surveys.stream()
                .filter(s -> s.getId().equals(idSurvey))
                .findFirst().orElse(null))
                .getQuestions()
                .addAll(newQuestions);
        if(isAdd){
            return newQuestions;
        }
        return null;
    }

}
