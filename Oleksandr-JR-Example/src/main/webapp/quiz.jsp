<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Question" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Quiz</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        .question { margin-bottom: 20px; }
        .question-title { font-weight: bold; }
        .answer { margin-left: 20px; }
    </style>
</head>
<body>

<h1>Mini Quiz</h1>

<%
    ArrayList<Question> questions = (ArrayList<Question>) request.getSession().getAttribute("questions");
    if (questions == null) {
%>
<p>No questions available.</p>
<%
} else {
%>

<form action="quiz" method="post">
    <%
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
    %>
    <div class="question">
        <div class="question-title">
            <%= (i + 1) %>. <%= q.getText() %>
        </div>
        <%
            for (int j = 0; j < q.getAnswers().size(); j++) {
                String answer = q.getAnswers().get(j);
        %>
        <div class="answer">
            <label>
                <input type="radio" name="q<%= i %>" value="<%= j %>" required>
                <%= answer %>
            </label>
        </div>
        <%
            }
        %>
    </div>
    <%
        }
    %>
    <input type="submit" value="Submit Quiz">
</form>

<%
    }
%>

</body>
</html>