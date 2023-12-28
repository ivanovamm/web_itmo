<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.lab2_4.Point" %>

<!DOCTYPE html>
<html lang="ru-RU">

<head>
    <meta charset="UTF-8">
    <link href="style.css" rel="stylesheet">
    <script src="js/hello.js"></script>
    <title>I LOVE WEB STILL</title>
</head>
<header align="center">
    Результаты проверки
</header>
<body>
<div class="box">
<table id="result_table" align="centre">
    <thead>
    <th>X</th>
    <th>Y</th>
    <th>R</th>
    <th>Result</th>
    </thead>
    <%
        List<Point> points = (List<Point>) application.getAttribute("points");
        for (Point point : points) {
    %>
    <tbody>
    <td>
        <%= point.getX() %>
    </td>
    <td>
        <%= point.getY() %>
    </td>
    <td>
        <%= point.getR() %>
    </td>
    <td>
        <%= point.isInArea() ? "<span class=\"success\">true</span>"
                : "<span class=\"fail\">false</span>" %>
    </td>
    </tbody>
    <% } %>

    <div id="back">
        <a href="index.jsp">Вернуться к форме</a>
    </div>

</table>
</div>
</body>
</html>

