package com.jay.quiz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Category {
    private final String name;
    private final List<Question> questions;

    @JsonCreator
    public Category(
        @JsonProperty("name") String name, 
        @JsonProperty("questions") List<Question> questions) {
        
        this.name = name;
        this.questions = questions;
    }

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}