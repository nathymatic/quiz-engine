package com.jay.quiz.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Question {
    private final String text;
    private final List<String> choices;
    private final int correctIndex;

    @JsonCreator
    public Question(
        @JsonProperty("text") String text,
        @JsonProperty("answers") List<String> choices,
        @JsonProperty("correctIndex") int correctIndex) {
        
        if (choices.size() != 4) {
            throw new IllegalArgumentException("A question must have exactly 4 choices.");
        }

        if (correctIndex < 0 || correctIndex >= choices.size()) {
            throw new IllegalArgumentException("Correct answer index must be between 0 and 3.");
        }

        this.text = text;
        this.choices = List.copyOf(choices);
        this.correctIndex = correctIndex;
    }

    public String getText() {
        return text;
    }

    public List<String> getChoices() {
        return choices;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }
}