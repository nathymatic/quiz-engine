package com.jay.quiz.ui;

import java.util.Scanner;

import com.jay.quiz.model.Question;

public class ConsoleUi implements AutoCloseable {
    private final Scanner scanner;

    public ConsoleUi(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayQuestion(Question question) {
        System.out.println();
        System.out.println(question.getText());

        for (int i = 0; i < question.getChoices().size(); i++) {
            System.out.printf("%d. %s%n", i + 1, question.getChoices().get(i));
        }
    }

    public int getAnswer() {
        while (true) {
            System.out.println("Answer: ");

            if (scanner.hasNextInt()) {
                int answer = scanner.nextInt();
                scanner.nextLine();

                return answer - 1;
            }

            System.out.println("Please enter a number.");
            scanner.nextLine();
        }
    }

    public void displayCorrect() {
        System.out.println("Correct!");
    }

    public void displayIncorrect() {
        System.out.println("Incorrect!");
    }

    public void displayScore(int score, int total) {
        System.out.printf("%nFinal score: %d/%d%n", score, total);
    }

    @Override 
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
}