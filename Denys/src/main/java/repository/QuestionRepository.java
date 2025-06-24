package repository;

import model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionRepository {
    private static QuestionRepository INSTANCE;

    private final ArrayList<Question> questions = new ArrayList<>();

    private QuestionRepository() {
        questions.add(Question.builder()
                .text("Вам пропонують почати дослідження всесвіту. Прийняти пропозицію?")
                .answers(new ArrayList<>(List.of("Прийняти", "Відхилити")))
                .nextQuestions(new ArrayList<>(List.of(1, 2)))
                .build());

        questions.add(Question.builder()
                .text("Штовхнути темну матерію?")
                .answers(new ArrayList<>(List.of("Так", "Ні")))
                .nextQuestions(new ArrayList<>(List.of(3, 5)))
                .build());

        questions.add(Question.builder()
                .text("У результаті вашого відказу, дослідження було перенесене на великий термін. Поразка")
                .answers(new ArrayList<>())
                .nextQuestions(new ArrayList<>())
                .build());

        questions.add(Question.builder()
                .text("Випарувалася невелика чорна діра, та залишився новий елемент під назвою — частка Бога! Що робимо далі?")
                .answers(new ArrayList<>(List.of("Збільшити навантаження!", "Дослідити частку")))
                .nextQuestions(new ArrayList<>(List.of(4, 9)))
                .build());

        questions.add(Question.builder()
                .text("Чорна діра вбила все живе! Поразка.")
                .answers(new ArrayList<>())
                .nextQuestions(new ArrayList<>())
                .build());

        questions.add(Question.builder()
                .text("Що будете робити?")
                .answers(new ArrayList<>(List.of("Розвинути проект світлої матерії", "До дому.")))
                .nextQuestions(new ArrayList<>(List.of(6, 2)))
                .build());

        questions.add(Question.builder()
                .text("Скільки матерії додати до коллайдеру?")
                .answers(new ArrayList<>(List.of("6 ммоль", "100 ммоль")))
                .nextQuestions(new ArrayList<>(List.of(7, 8)))
                .build());

        questions.add(Question.builder()
                .text("Людство позбулося енергетичної кризи. Перемога!")
                .answers(new ArrayList<>())
                .nextQuestions(new ArrayList<>())
                .build());

        questions.add(Question.builder()
                .text("Нейтронна бомба вбила все живе! Поразка!")
                .answers(new ArrayList<>())
                .nextQuestions(new ArrayList<>())
                .build());

        questions.add(Question.builder()
                .text("Людство позбулося енергетичної кризи. Перемога!")
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
