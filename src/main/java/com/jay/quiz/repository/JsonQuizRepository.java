package com.jay.quiz.repository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jay.quiz.model.Quiz;

public class JsonQuizRepository implements QuizRepository {
    private final ObjectMapper mapper;
    private final Path file;

    public JsonQuizRepository(Path file) {
        this.mapper = new ObjectMapper();
        this.file = file;
    }

    @Override
    public List<Quiz> load() throws IOException {
        return mapper.readValue(
            file.toFile(),
            new TypeReference<List<Quiz>>() {}
        );
    }

    @Override
    public void save(List<Quiz> quizzes) throws IOException {
        mapper.writerWithDefaultPrettyPrinter()
            .writeValue(
                file.toFile(),
                quizzes
        );
    }
}