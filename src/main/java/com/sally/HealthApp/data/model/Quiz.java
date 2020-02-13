package com.sally.HealthApp.data.model;

import java.util.ArrayList;

public class Quiz {

    private String QuizName = "";
    ArrayList<Questions> questions ;

    public Quiz(String QuizName, ArrayList<Questions> questions){
        this.QuizName = QuizName;
        this.questions = questions;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String quizName) {
        QuizName = quizName;
    }

    public ArrayList<Questions> getListOfQuestions() {
        return questions;
    }

    public void setListOfQuestions(ArrayList<Questions> questions) {
        this.questions = questions ;
    }

}
