package com.jay.quiz.service;

import com.jay.quiz.model.Question;
import com.jay.quiz.model.Quiz;

public class QuizSession {
    private final Quiz quiz;
    private int currentQuestionIndex;
    private int score;

    public QuizSession(Quiz quiz) {
        this.quiz = quiz;
    }

    // * Methods

    public boolean answer(int answerIndex) {
        Question question = getCurrentQuestion();

        if (answerIndex < 0 || answerIndex >= question.getChoices().size()) {
            throw new IllegalArgumentException("Invalid answer index.");
        }

        boolean correct = answerIndex == question.getCorrectIndex();

        if (correct) {
            score++;
        }

        currentQuestionIndex++;

        return correct;
    }

    // * Properties

    public Quiz getQuiz() {
        return quiz;
    }

    public Question getCurrentQuestion() {
        return quiz
                .getCategory()
                .getQuestions()
                .get(currentQuestionIndex);
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getScore() {
        return score;
    }

    public boolean isFinished() {
        return currentQuestionIndex >= quiz.getCategory().getQuestions().size();
    }
}