<%--
  Created by IntelliJ IDEA.
  User: Maria
  Date: 20.11.2023
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.lab2_4.ResultBean" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab2_4.ResultBean" %>
<jsp:useBean id="result" class="com.example.lab2_4.ResultBean" scope="session"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="author" content="Мария Иванова">
    <meta name="description" content="Лабораторная работа №2 по веб-программированию">
    <title>I LOVE WEB</title>
    <link rel="stylesheet" href="./style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" defer></script>
    <script src="js/hello.js" defer></script>
    <script src="js/graph.js" defer></script>
</head>
<body>
<header align="center">
    Иванова М.М., Группа P3208, Вариант 1810
</header>
<noscript>включите джава скрипт</noscript>
<div class="x"><b>Input X:</b>
    <input type="text" size="40" id="x_value" , placeholder="(-5 ... 3)">
</div>
<form method="post" id="form">
    <div align="right" class="y">
        <b>Input Y:</b>
        <input type="checkbox" name="optionY" value="-2" id="y_value_-2" onchange="uncheckAllY(this)"> -2
        <input type="checkbox" name="optionY" value="-1.5" onchange="uncheckAllY(this)"> -1.5
        <input type="checkbox" name="optionY" value="-0.5" onchange="uncheckAllY(this)"> -0.5
        <input type="checkbox" name="optionY" value="0" onchange="uncheckAllY(this)"> 0
        <input type="checkbox" name="optionY" value="0.5" onchange="uncheckAllY(this)"> 0.5
        <input type="checkbox" name="optionY" value="1" onchange="uncheckAllY(this)"> 1
        <input type="checkbox" name="optionY" value="1.5" onchange="uncheckAllY(this)"> 1.5
        <input type="checkbox" name="optionY" value="2" onchange="uncheckAllY(this)"> 2
    </div>
    <div class="r" align="right"><b>Input R:</b>
        <input type="checkbox" name="optionR" value="1" onchange="uncheckAllR(this)"> 1
        <input type="checkbox" name="optionR" value="1.5" onchange="uncheckAllR(this)"> 1.5
        <input type="checkbox" name="optionR" value="2" onchange="uncheckAllR(this)"> 2
        <input type="checkbox" name="optionR" value="2.5" onchange="uncheckAllR(this)"> 2.5
        <input type="checkbox" name="optionR" value="3" onchange="uncheckAllR(this)"> 3
    </div>
</form>
<div align="right">
    <button class="check" id="check" onclick="check_values()">Check</button>
</div>
<canvas width="400" height="400" id="graph"></canvas>
<div>
    <table id="table">
        <thead>
        <tr>
            <th>R</th>
            <th>X</th>
            <th>Y</th>
            <th>Status</th>
            <th>Time</th>
            <th>Execution time</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
        <core:forEach var="result" items="${results.results}">
            <tr>
                <td>${result.x}</td>
                <td>${result.y}</td>
                <td>${result.r}</td>
                <td>${result.isHit ? "Success" : "Failed"}</td>
            </tr>
        </core:forEach>
    </table>
</div>
<div align="left">
    <button id="clear" class="clear" onclick="clear_table()">Clear</button>
</div>
</body>
</html>