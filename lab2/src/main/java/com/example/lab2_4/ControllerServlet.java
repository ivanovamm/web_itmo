package com.example.lab2_3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    String validate;
    String x;
    String y;
    String r;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validate = request.getParameter("validate");
        x = request.getParameter("x");
        y = request.getParameter("y");
        r = request.getParameter("r");

        if (checkNull()) {
            if (validate.equals("reload")) {
                response.sendRedirect("index.jsp");
            } else {
                request.getRequestDispatcher("/AreaCheckServlet").forward(request, response);
            }
        } else {
            response.setStatus(422);
        }
    }

    private boolean checkNull() {
        if (validate != null && !validate.trim().equals("") && (x != null && y != null && r != null) && (!x.trim().equals("")
                && !y.trim().equals("") && !r.trim().equals(""))) {
            return true;
        } else {
            return false;
        }
    }
}

