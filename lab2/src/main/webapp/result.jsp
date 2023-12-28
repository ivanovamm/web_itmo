<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.lab2_4.Point" %>

<!DOCTYPE html>
<html lang="ru-RU">

<head>
    <meta charset="UTF-8">
    <link href="stylesheets/styles.css" rel="stylesheet">
    <title>Результаты проверки | Веб-программирование</title>
</head>

<body>


<table id="mainTable" class="shaded">
    <thead>
    <td colspan="5">
        <h3>Результаты проверки:</h3>
    </td>
    </thead>

    <tbody>
    </tbody>

    <tfoot>
    <tr>
        <td colspan="5" id="outputContainer">
            <%
                List<Point> points = (List<Point>) application.getAttribute("points");
                if (points == null || points.isEmpty()) {
            %>
            <h4>
                <span id="notifications" class="outputStub notification">Нет результатов</span>
            </h4>
            <table id="outputTable">
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Результат</th>
                </tr>
            </table>
            <% } else { %>
            <table id='outputTable'>
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Результат</th>
                </tr>
                <% for (Point point : points) { %>
                <tr>
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
                </tr>
                <% } %>
            </table>
            <% } %>
        </td>
    </tr>
    <tr>
        <td>
            <div id="goBack">
                <a href="index.jsp">Вернуться к форме</a>
            </div>
        </td>
    </tr>
    </tfoot>

</table>


<script src="script.js"></script>
</body>

</html>

