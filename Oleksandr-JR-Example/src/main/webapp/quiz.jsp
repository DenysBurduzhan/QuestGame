<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Question" %>
<%@ page import="java.util.*" %>
<%
    Question question = (Question) request.getAttribute("question");
    int qIndex = (Integer) request.getAttribute("qIndex");
    Integer gamesPlayed = (Integer) session.getAttribute("gamesPlayed");
    if (gamesPlayed == null) gamesPlayed = 0;

    String text = question.getText().toLowerCase();
    boolean isEnding = text.contains("поразка") || text.contains("перемога");
%>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Квест</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h2><%= question.getText() %></h2>

<p>Кількість пройдених ігор: <strong><%= gamesPlayed %></strong></p>

<% if (isEnding) { %>
<a href="quiz?restart=true" class="button">Почати заново</a>
<% } else { %>
<form method="post" action="quiz">
    <input type="hidden" name="qIndex" value="<%= qIndex %>"/>
    <%
        List<String> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
    %>
    <button type="submit" name="answerIndex" value="<%= i %>" class="button">
        <%= answers.get(i) %>
    </button>
    <%
        }
    %>
</form>
<% } %>

</body>
</html>


