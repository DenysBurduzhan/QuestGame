package model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class Question {
    private String text;
    private ArrayList<String> answers;
    private ArrayList<Integer> nextQuestions;
    private Integer correctAnswer;
}