package com.example.lab2_4;//package com.example.lab2_4;
//
//import com.google.gson.Gson;
//import jakarta.servlet.ServletContext;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.HttpMethodConstraint;
//import jakarta.servlet.annotation.ServletSecurity;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.util.*;
//
//
//@WebServlet("/check")
////@ServletSecurity(
////        httpMethodConstraints = {
////                @HttpMethodConstraint(value = "POST", rolesAllowed = "admin")
////        }
////)
//public class AreaCheckServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//
//    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            double x = Double.parseDouble(request.getParameter("x"));
//            double y = Double.parseDouble(request.getParameter("y"));
//            int r = Integer.parseInt(request.getParameter("r"));
//            ResultBean point = new ResultBean(x, y, r);
//
//            List<ResultBean> points = getPoints(request.getServletContext());
//            points.add(point);
//
//            String action = request.getParameter("action");
//            if ("submit".equals(action)) {
//                forwardToResult(request, response, x, y, r, point);
//            } else if ("check".equals(action)) {
//                sendJsonResponse(response, x, y, r, point);
//            }
//        } catch (NumberFormatException e) {
//            forwardToIndex(request, response);
//        } catch (IOException e) {
//            forwardToIndex(request, response);
//        }
//    }
//
//    @SuppressWarnings("unchecked")
//    private List<ResultBean> getPoints(ServletContext context) {
//        List<ResultBean> points = (List<ResultBean>) context.getAttribute("points");
//        if (points == null) {
//            points = new ArrayList<>();
//            context.setAttribute("points", points);
//        }
//        return points;
//    }
//
//    private void forwardToResult(HttpServletRequest request, HttpServletResponse response, double x, double y, int r, ResultBean point)
//            throws ServletException, IOException {
//        request.setAttribute("x", x);
//        request.setAttribute("y", y);
//        request.setAttribute("r", r);
//        request.setAttribute("result", point.inArea(x, y, r));
//        request.getRequestDispatcher("./result.jsp").forward(request, response);
//    }
//
//    private void sendJsonResponse(HttpServletResponse response, double x, double y, int r, ResultBean point) throws IOException {
//        Map<String, Object> json = new HashMap<>();
//        json.put("x", x);
//        json.put("y", y);
//        json.put("r", r);
//        json.put("result", point.inArea(x, y, r));
//        response.setContentType("application/json");
//        response.getWriter().write(new Gson().toJson(json));
//    }
//
//    private void forwardToIndex(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        request.getRequestDispatcher("./index.jsp").forward(request, response);
//    }
//}

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/checkArea")
public class AreaCheckServlet extends HttpServlet {
    private static final String ACTION_SUBMIT_FORM = "submitForm";
    private static final String ACTION_CHECK_POINT = "checkPoint";
    private static final String POINTS_ATTR = "points";

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
            int r = Integer.parseInt(request.getParameter("r"));
            Point point = new Point(x, y, r);

            List<Point> points = getPoints(request.getServletContext());
            points.add(point);

            String action = request.getParameter("action");
            if (ACTION_SUBMIT_FORM.equals(action)) {
                forwardToResult(request, response, x, y, r, point);
            } else if (ACTION_CHECK_POINT.equals(action)) {
                sendJsonResponse(response, x, y, r, point);
            }
        } catch (NumberFormatException e) {
            forwardToIndex(request, response);
        } catch (IOException e) {
            forwardToIndex(request, response);
        }
    }

    @SuppressWarnings("unchecked")
    private List<Point> getPoints(ServletContext context) {
        List<Point> points = (List<Point>) context.getAttribute(POINTS_ATTR);
        if (points == null) {
            points = new ArrayList<>();
            context.setAttribute(POINTS_ATTR, points);
        }
        return points;
    }

    private void forwardToResult(HttpServletRequest request, HttpServletResponse response, double x, double y, int r, Point point)
            throws ServletException, IOException {
        request.setAttribute("x", x);
        request.setAttribute("y", y);
        request.setAttribute("r", r);
        request.setAttribute("result", point.isInArea());
        request.getRequestDispatcher("./result.jsp").forward(request, response);
    }

    private void sendJsonResponse(HttpServletResponse response, double x, double y, int r, Point point) throws IOException {
        Map<String, Object> json = new HashMap<>();
        json.put("x", x);
        json.put("y", y);
        json.put("r", r);
        json.put("result", point.isInArea());
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(json));
    }

    private void forwardToIndex(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("./index.jsp").forward(request, response);
    }
}

