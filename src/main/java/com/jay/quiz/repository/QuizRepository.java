package com.jay.quiz.repository;

import java.io.IOException;
import java.util.List;

import com.jay.quiz.model.Quiz;

public interface QuizRepository {
    List<Quiz> load() throws IOException;

    void save(List<Quiz> quizzes) throws IOException;
}