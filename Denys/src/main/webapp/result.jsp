<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="uk">
<head>
    <meta charset="UTF-8">
    <title>Результат</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<h1>Гра завершена!</h1>

<p>Дякуємо за гру!</p>
<p>Ви вже зіграли <strong><%= session.getAttribute("gamesPlayed") != null ? session.getAttribute("gamesPlayed") : 1 %></strong> раз(ів).</p>

<a href="quiz?restart=true" class="button">Почати заново</a>

</body>
</html>
