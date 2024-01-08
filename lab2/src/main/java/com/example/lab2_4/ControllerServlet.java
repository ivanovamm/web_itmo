package com.example.lab2_4;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/controller")
public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!isValidRequest(request)) {
            return;
        }

        try {
            if (
                    Double.parseDouble(request.getParameter("x")) < -5
                            || Double.parseDouble(request.getParameter("x")) > 3
            ) {
                return;
            }

            Double.parseDouble(request.getParameter("y"));
            Double.parseDouble(request.getParameter("r"));

            response.sendRedirect("./checkArea?" + request.getQueryString());
        } catch (NumberFormatException e) {

        } catch (Exception e) {

        }
    }

    private boolean isValidRequest(HttpServletRequest request) {
        return isParameterValid(request, "x") &&
                isParameterValid(request, "y") &&
                isParameterValid(request, "r");
    }

    private boolean isParameterValid(HttpServletRequest request, String paramName) {
        String paramValue = request.getParameter(paramName);
        return paramValue != null && !paramValue.isEmpty();
    }


}

