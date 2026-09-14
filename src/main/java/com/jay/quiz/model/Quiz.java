package com.jay.quiz.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Quiz {
    private final String name;
    private final Category category;

    @JsonCreator 
    public Quiz(
        @JsonProperty("name") String name,
        @JsonProperty("category") Category category) {
            
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}