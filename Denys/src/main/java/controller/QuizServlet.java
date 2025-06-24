package controller;

import model.Question;
import repository.QuestionRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "QuizServlet", value = "/quiz")
public class QuizServlet extends HttpServlet {
    private static final QuestionRepository questionRepository = QuestionRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if ("true".equals(req.getParameter("restart"))) {
            session.removeAttribute("currentIndex");

            Integer games = (Integer) session.getAttribute("gamesPlayed");
            if (games == null) games = 0;
            session.setAttribute("gamesPlayed", games + 1);
        }

        Integer currentIndex = (Integer) session.getAttribute("currentIndex");
        if (currentIndex == null) {
            currentIndex = 0;
            session.setAttribute("currentIndex", currentIndex);
        }

        Question question = questionRepository.getAll().get(currentIndex);
        req.setAttribute("question", question);
        req.setAttribute("qIndex", currentIndex);

        getServletContext().getRequestDispatcher("/quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        int qIndex = Integer.parseInt(req.getParameter("qIndex"));
        int answerIndex = Integer.parseInt(req.getParameter("answerIndex"));

        Question question = questionRepository.getAll().get(qIndex);

        if (question.getNextQuestions() == null || question.getNextQuestions().isEmpty()) {
            getServletContext().getRequestDispatcher("/result.jsp").forward(req, resp);
            return;
        }

        int nextIndex = question.getNextQuestions().get(answerIndex);
        session.setAttribute("currentIndex", nextIndex);

        resp.sendRedirect("quiz");
    }
}


