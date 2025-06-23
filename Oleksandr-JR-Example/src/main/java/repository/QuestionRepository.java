package repository;

import model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {
    private static QuestionRepository INSTANCE;

    private final ArrayList<Question> questions = new ArrayList<>();


    private QuestionRepository() {
        questions.add(Question.builder()
                .text("Ви втрачаєте пам'ять. Прийняти виклик НЛО?")
                .answers(new ArrayList<>(List.of("Прийняти виклик", "Відхилити виклик")))
                .nextQuestions(new ArrayList<>(List.of(1, 5)))
                .build());

        questions.add(Question.builder()
                .text("Ви прийняли виклик. Піднятися на капітанський місток?")
                .answers(new ArrayList<>(List.of("Піднятися на місток", "Відмовитися")))
                .nextQuestions(new ArrayList<>(List.of(2, 4)))
                .build());

        questions.add(Question.builder()
                .text("Ви піднялися на місток. Хто ви?")
                .answers(new ArrayList<>(List.of("Розповісти правду", "Збрехати")))
                .nextQuestions(new ArrayList<>(List.of(6, 7)))
                .build());

        questions.add(Question.builder()
                .text("Якийсь інший варіант?")
                .answers(new ArrayList<>(List.of("Тест1", "Тест2")))
                .nextQuestions(new ArrayList<>(List.of(0, 0)))
                .build());

        questions.add(Question.builder()
                .text("Ви не пішли на переговори. Поразка.")
                .answers(new ArrayList<>())
                .nextQuestions(new ArrayList<>())
                .build());

        questions.add(Question.builder()
                .text("Ви відхилили виклик. Поразка.")
                .answers(new ArrayList<>())
                .nextQuestions(new ArrayList<>())
                .build());

        questions.add(Question.builder()
                .text("Вас повернули додому. Перемога!")
                .answers(new ArrayList<>())
                .nextQuestions(new ArrayList<>())
                .build());

        questions.add(Question.builder()
                .text("Ваша брехня була викрита. Поразка.")
                .answers(new ArrayList<>())
                .nextQuestions(new ArrayList<>())
                .build());
    }

    public static QuestionRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QuestionRepository();
        }

        return INSTANCE;

    }


    public ArrayList<Question> getAll() {
        return questions;
    }


}
