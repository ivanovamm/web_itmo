package com.example.lab2_4;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/checkArea")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            double x = Double.parseDouble(request.getParameter("x"));
            double y = Double.parseDouble(request.getParameter("y"));
            double r = Double.parseDouble(request.getParameter("r"));
            Point point = new Point(x, y, r);

            List<Point> points = getPoints(request.getServletContext());
            points.add(point);

            String action = request.getParameter("action");
            if ("submitForm".equals(action)) {
                GetResult(request, response, x, y, r, point);
            }

        } catch (NumberFormatException e) {
        } catch (IOException e) {
        }
    }

    private List<Point> getPoints(ServletContext context) {
        List<Point> points = (List<Point>) context.getAttribute("points");
        if (points == null) {
            points = new ArrayList<>();
            context.setAttribute("points", points);
        }
        return points;
    }

    private void GetResult(HttpServletRequest request, HttpServletResponse response, double x, double y, double r, Point point)
            throws ServletException, IOException {
        request.setAttribute("x", x);
        request.setAttribute("y", y);
        request.setAttribute("r", r);
        request.setAttribute("result", point.isInArea());
        request.getRequestDispatcher("./result.jsp").forward(request, response);
    }

}

