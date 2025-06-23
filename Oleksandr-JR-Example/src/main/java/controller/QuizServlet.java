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
        String questionIndexStr = req.getParameter("q");
        int questionIndex = questionIndexStr != null ? Integer.parseInt(questionIndexStr) : 0;

        Question question = questionRepository.getAll().get(questionIndex);
        req.setAttribute("question", question);
        req.setAttribute("qIndex", questionIndex);

        getServletContext().getRequestDispatcher("/quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int currentIndex = Integer.parseInt(req.getParameter("qIndex"));
        int answerIndex = Integer.parseInt(req.getParameter("answerIndex"));

        Question currentQuestion = questionRepository.getAll().get(currentIndex);
        int nextIndex = currentQuestion.getNextQuestions().get(answerIndex);

        resp.sendRedirect("quiz?q=" + nextIndex);
    }
}

