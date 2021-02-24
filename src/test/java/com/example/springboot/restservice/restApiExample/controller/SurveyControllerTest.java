package com.example.springboot.restservice.restApiExample.controller;

import com.example.springboot.restservice.restApiExample.model.Question;
import com.example.springboot.restservice.restApiExample.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

//@SpringBootTest //--> this annotations tells spring to loads complete application and injects all the beans. This can be slow
@AutoConfigureMockMvc
@WebMvcTest(SurveyController.class) //--> this annotation is used to test the controller layer. We can provide required dependencies by using mocks objects
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;//Used to do the call to the services

    @MockBean
    private SurveyService surveyServiceMock;

    @Test
    public void testRetrieveASpecificQuestionOfASurvey() throws Exception {
        //With mock we use the specific data or dependencies in the controller class
        Question mockQuestion = new Question("Question1",
                "Largest Country in the World", "Russia", Arrays.asList(
                "India", "Russia", "United States", "Chile"));
        Mockito
                .when(surveyServiceMock.retrieveQuestion(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(mockQuestion);

        //Make a call endpoint service
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/surveys/Survey1/questions/Question1")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc
                .perform(requestBuilder)
                .andReturn();
        String expected = "{\"id\":\"Question1\", \"description\":\"Largest Country in the World\", \"correctAnswer\":\"Russia\"}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

    }
}
