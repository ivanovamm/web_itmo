package com.example.lab2_3;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import static java.lang.Math.pow;

@WebServlet("/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
    LinkedList<String> response = new LinkedList<>();
    String message;
    Double x, y, r;
    String check;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = request.getServletContext();
        check = request.getParameter("check");
        x = Double.parseDouble(request.getParameter("x"));
        y = Double.parseDouble(request.getParameter("y"));
        r = Double.parseDouble(request.getParameter("r"));

        if (checkNull()) {
            if (check.equals("clear")) {
                this.response = new LinkedList<>();
                servletContext.setAttribute("response", this.response);
                response.sendRedirect("index.jsp");
                return;
            }
            if (checkRange()) {
                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                long startTime = System.nanoTime();
                validate(x, y, r);
                long timeResponse = (System.nanoTime() - startTime);
                this.response.addFirst("<tr><td>" + x + "</td>" +
                        "<td>" + y + "</td>" +
                        "<td>" + r + "</td>" +
                        "<td>" + currentTime + "</td>" +
                        "<td>" + timeResponse + " ns" + "</td>" +
                        "<td>" + message + "</td></tr>");
            } else {
                message = "<td>Value is incorrect!</td>";
                this.response.addFirst("<tr>" + message + message + message + message + message + message + "</tr>");
            }
            servletContext.setAttribute("response", this.response);
            response.sendRedirect("index.jsp");
        } else {
            response.setStatus(422);
        }
    }

    void validate(double x, double y, double r) {
        boolean area1;
        boolean area2;
        boolean area3;
        area1 = (((-(r)) <= (x)) && ((x) <= (0))) && (((0) <= (y)) && ((y) <= (r / 2)));
        area2 = (x >= 0) && (y >= 0) && (x <= r) && (y <= r / 2);
        double x1;
        double y1;
        x1 = pow(x, 2);
        y1 = pow(y, 2);
        area3 = ((x1 + y1 == r) && (x >= 0) && (y <= 0));
        if (area1 || area2 || area3) {
            message = "<span style='color: green'>Success</span>";
        } else {
            message = "<span style='color: red'>Failed</span>";
        }
    }

    boolean checkRange() {
        if (x <= -5 || x >= 3) {
            return false;
        }
        if (!((y == -2) || (y == -1.5) || (y == -1) || (y == -0.5) || (y == 0) || (y == 0.5) || (y == 1) || (y == 1.5) || (y == 2))) {
            return false;
        }
        if (!((r == 1) || (r == 1.5) || (r == 2) || (r == 2.5) || (r == 3))) {
            return false;
        }
        return true;
    }

    boolean checkNull() {
        if (check != null && !check.trim().equals("") && (x != null && y != null && r != null)) {
            return true;
        }
        return false;
    }
}
