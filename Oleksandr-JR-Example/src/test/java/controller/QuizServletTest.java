package controller;

import model.Question;
import repository.QuestionRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class QuizServletTest {

    @InjectMocks
    private QuizServlet realServlet;

    private QuizServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private HttpSession session;

    @Mock
    private ServletContext servletContext;

    @Mock
    private RequestDispatcher dispatcher;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        servlet = spy(realServlet);
        when(request.getSession()).thenReturn(session);
        when(request.getServletContext()).thenReturn(servletContext);
        doReturn(servletContext).when(servlet).getServletContext();
    }

    @Test
    public void testDoGet_NewGame_ShouldStartAtIndexZero() throws Exception {
        when(session.getAttribute("currentIndex")).thenReturn(null);
        when(servletContext.getRequestDispatcher("/quiz.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        verify(session).setAttribute("currentIndex", 0);
        verify(dispatcher).forward(request, response);
    }

    @Test
    public void testDoPost_ValidAnswer_RedirectsToNextQuestion() throws Exception {
        QuestionRepository repo = QuestionRepository.getInstance();
        repo.getAll().clear();
        repo.getAll().add(
                Question.builder()
                        .text("Test?")
                        .answers(new ArrayList<>(List.of("Yes", "No")))
                        .nextQuestions(new ArrayList<>(List.of(1, 2)))
                        .build()
        );

        when(request.getParameter("qIndex")).thenReturn("0");
        when(request.getParameter("answerIndex")).thenReturn("1");
        when(request.getSession()).thenReturn(session);

        servlet.doPost(request, response);

        verify(session).setAttribute("currentIndex", 2);
        verify(response).sendRedirect("quiz");
    }
}

