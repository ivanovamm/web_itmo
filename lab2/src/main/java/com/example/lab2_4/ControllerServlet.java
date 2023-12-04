package com.example.lab2_4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ControllerServlet", urlPatterns = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    String check;
    String x;
    String y;
    String r;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        check = request.getParameter("check");
        x = request.getParameter("x");
        y = request.getParameter("y");
        r = request.getParameter("r");
        if (check.equals("submit")) {
            request.getRequestDispatcher("/AreaCheckServlet").forward(request, response);
        }
        if (check != null && !check.trim().equals("")) {
            if (check.equals("reload")) {
                response.sendRedirect("index.jsp");
            } else {
                request.getRequestDispatcher("/AreaCheckServlet").forward(request, response);
            }
        } else {
            response.setStatus(422);
        }
    }

}

