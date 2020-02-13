package com.sally.HealthApp.controller;

import com.sally.HealthApp.data.model.Quiz;
import com.sally.HealthApp.data.model.ResponseModel;
import com.sally.HealthApp.service.QuizService;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/quiz",produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "QuizControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
public class QuizController {

    //request mapping- it routes to the locaton of which method we want.
    @RequestMapping(value  ="/getQuizNames",method = RequestMethod.GET)
    @ResponseBody

    // give a simple java method header
    // public ArrayList<String> getQuizNames()
    // convert that to a server method header
    public ResponseEntity<?> getQuizNames(){
        QuizService quizService = QuizService.getInstance();
        ArrayList<String> names = quizService.getNames();
        ResponseModel responseModel;
        if(names.isEmpty()){
            responseModel = new ResponseModel(false,0,"No qizzes found");
            return new ResponseEntity<>(responseModel, HttpStatus.ACCEPTED);
        }else{
            responseModel = new ResponseModel(true,10,"Names found");
            // the data that we respond back with
            responseModel.setData(names);
            // this is a wrapper this is similar to boxing an item/iinfo
            return new ResponseEntity<>(responseModel,HttpStatus.ACCEPTED);
        }
    }

    // Step one write the request mapping
    @RequestMapping(value = "/getQuiz",produces = MediaType.APPLICATION_JSON_VALUE)
    // step two have the response body
    @ResponseBody
    //write a method such that given a quiz name we retrieve that said quiz
    public ResponseEntity <?> getQuiz(@RequestParam(value = "name") String name) {
        ResponseModel responseModel;
        if(name.isEmpty()) {
            responseModel = new ResponseModel(false,0,"No quiz inputed");
            return new ResponseEntity<>(responseModel, HttpStatus.ACCEPTED);
        }

        QuizService quizService = QuizService.getInstance();
        ArrayList<String> names = quizService.getNames();

        for(String i  : names) {
            if(name.equals(i)){
                //get quiz
                Quiz toBeReturned = quizService.getQuiz(name);
                if(toBeReturned != null) {
                    responseModel = new ResponseModel(true, 10, "Quiz found");
                    responseModel.setData(toBeReturned);
                    return new ResponseEntity<>(responseModel, HttpStatus.ACCEPTED);
                }
            }
        }

        responseModel = new ResponseModel(false,0,"No quiz found");
        return new ResponseEntity<>(responseModel, HttpStatus.ACCEPTED);


    }

}
