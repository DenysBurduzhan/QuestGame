<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Result</title>
</head>
<body>

<h1>Quiz Result</h1>

<p>You scored <strong><%= request.getAttribute("score") %></strong> out of <strong><%= request.getAttribute("total") %></strong>.</p>

<a href="quiz?restart=true" class="button">Почати заново</a>

</body>
</html>
