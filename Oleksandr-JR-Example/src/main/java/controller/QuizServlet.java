package controller;

import model.Question;
import repository.QuestionRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "QuizServlet", value = "/quiz")
public class QuizServlet extends HttpServlet {
    private static final QuestionRepository questionRepository = QuestionRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Question> questions = questionRepository.getAll();


        req.getSession().setAttribute("questions", questions);

        getServletContext().getRequestDispatcher("/quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Question> questions = (ArrayList<Question>) req.getSession().getAttribute("questions");

        if (questions == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "No questions found in session.");
            return;
        }

        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            String paramName = "q" + i;
            String answerStr = req.getParameter(paramName);
            if (answerStr != null) {
                int selectedAnswer = Integer.parseInt(answerStr);
                if (selectedAnswer == questions.get(i).getCorrectAnswer()) {
                    score++;
                }
            }
        }

        req.setAttribute("score", score);
        req.setAttribute("total", questions.size());

        getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
    }

}
