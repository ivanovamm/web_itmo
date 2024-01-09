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
        if (!checkRequest(request)) {
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

    private boolean checkRequest(HttpServletRequest request) {
        return checkValues(request, "x") &&
                checkValues(request, "y") &&
                checkValues(request, "r");
    }

    private boolean checkValues(HttpServletRequest request, String value) {
        String valueName = request.getParameter(value);
        return valueName != null && !valueName.isEmpty();
    }


}

