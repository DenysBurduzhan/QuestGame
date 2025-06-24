package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.QuestionRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionRepositoryTest {

    private QuestionRepository repository;

    @BeforeEach
    public void setup() {
        repository = QuestionRepository.getInstance();
        repository.getAll().clear();

        repository.getAll().add(
                Question.builder()
                        .text("Question 0?")
                        .answers(new ArrayList<>(List.of("Answer 1", "Answer 2")))
                        .nextQuestions(new ArrayList<>(List.of(1, 2)))
                        .build()
        );

        repository.getAll().add(
                Question.builder()
                        .text("Question 1?")
                        .answers(new ArrayList<>())
                        .nextQuestions(new ArrayList<>())
                        .build()
        );

        repository.getAll().add(
                Question.builder()
                        .text("Question 2?")
                        .answers(new ArrayList<>())
                        .nextQuestions(new ArrayList<>())
                        .build()
        );
    }

    @Test
    public void testQuestionsAreLoaded() {
        ArrayList<Question> questions = repository.getAll();
        assertFalse(questions.isEmpty(), "Questions should be loaded");
    }

    @Test
    public void testTwoAnswers() {
        Question q0 = repository.getAll().get(0);
        assertEquals(2, q0.getAnswers().size(), "First question should have two answers");
    }

    @Test
    public void testNavigation() {
        Question q0 = repository.getAll().get(0);
        int next = q0.getNextQuestions().get(0);
        Question q1 = repository.getAll().get(next);
        assertNotNull(q1, "Should navigate to next question");
    }

    @Test
    public void testHaveNoNext() {
        for (Question q : repository.getAll()) {
            if (q.getAnswers().isEmpty()) {
                assertTrue(q.getNextQuestions().isEmpty(), "Terminal questions should not have next steps");
            }
        }
    }
}
