<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Question" %>
<%@ page import="java.util.*" %>
<%
    Question question = (Question) request.getAttribute("question");
    int qIndex = (Integer) request.getAttribute("qIndex");
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Квест</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<h2><%= question.getText() %></h2>
<form method="post" action="quiz">
    <input type="hidden" name="qIndex" value="<%= qIndex %>"/>
    <%
        List<String> answers = question.getAnswers();
        for (int i = 0; i < answers.size(); i++) {
    %>
    <button type="submit" name="answerIndex" value="<%= i %>"><%= answers.get(i) %></button>
    <%
        }
    %>
</form>
</body>
</html>

