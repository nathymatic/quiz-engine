package com.jay.quiz.ui;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import com.jay.quiz.model.Quiz;
import com.jay.quiz.repository.JsonQuizRepository;
import com.jay.quiz.repository.QuizRepository;
import com.jay.quiz.service.QuizSession;

public class App {
    public static void main(String[] args) {
        QuizRepository repository =
            new JsonQuizRepository(Path.of("quizzes.json"));

        List<Quiz> quizzes;

        try {
            quizzes = repository.load();
        } catch (IOException e) {
            System.out.println("Failed to load quizzes: " + e.getMessage());
            return;
        }

        if (quizzes.isEmpty()) {
            System.out.println("No quizzes available.");
            return;
        }

        Quiz quiz = quizzes.get(0);
        QuizSession session = new QuizSession(quiz);

        try (ConsoleUi ui = new ConsoleUi(new Scanner(System.in))) {
            while (!session.isFinished()) {
                ui.displayQuestion(session.getCurrentQuestion());

                int answer = ui.getAnswer();
                boolean correct = session.answer(answer);

                if (correct) {
                    ui.displayCorrect();
                } else {
                    ui.displayIncorrect();
                }
            }

            ui.displayScore(
                    session.getScore(),
                    quiz.getCategory()
                        .getQuestions()
                        .size()
            );
        }

        System.out.println("Goodbye!");
    }
}