package com.example.lab2_4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


@WebServlet("/check")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = "admin")
        }
)
public class AreaCheckServlet extends HttpServlet {


    public static class CoordinatesValidator {
        private final double x, y, r;

        public CoordinatesValidator(double x, double y, double r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        public boolean checkData() {
            if (!checkX()) System.out.println("X validation haven't passed");
            if (!checkY()) System.out.println("Y validation haven't passed");
            if (!checkR()) System.out.println("R validation haven't passed");
            System.out.println(x);
            System.out.println(y);
            System.out.println(r);
            return checkX() && checkY() && checkR();
        }

        private boolean checkX() {
            return x > -5 && x < 3;
        }

        private boolean checkY() {
            List<? extends Number> YValues = Arrays.asList(-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2);
            return YValues.contains(y);
        }

        private boolean checkR() {
            List<? extends Number> YValues = Arrays.asList(1, 1.5, 2, 2.5, 3);
            return YValues.contains(r);
        }
//        protected void doGet(HttpServletRequest request, HttpServletResponse response)
//                throws ServletException, IOException{
//        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response) {
            LocalDateTime time = LocalDateTime.now();
            double x;
            double y;
            double r;
            try {
                x = ControllerServlet.getDouble(request, "x");
                y = ControllerServlet.getDouble(request, "y");
                r = ControllerServlet.getDouble(request, "r");

                CoordinatesValidator validator = new CoordinatesValidator(x, y, r);

                if (!validator.checkData()) {
                    System.out.println("Validation haven't passed");
                    return;
                }

                ResultBean bean = (ResultBean) request.getSession().getAttribute("results");
                if (bean == null) {
                    bean = new ResultBean();
                    request.getSession().setAttribute("results", bean);
                }

                class AreaChecker {
                    static boolean isInArea(double x, double y, double r) {
                        // Upper left corner with 1/4 circle of radius R
                        if (x <= 0 && y >= 0) {
                            return (x * x + y * y) <= r * r;
                        }
                        // Bottom left corner with triangle
                        if (x <= 0 && y <= 0) {
                            return (x >= -r) && (y >= -x - r);
                        }
                        // Bottom right corner with rectangle
                        if (x >= 0 && y <= 0) {
                            return (x <= r / 2) && (y >= -r);
                        }
                        // Upper right corner with nothing in it
                        return false;
                    }
                }


                ResultBean.Result result = new ResultBean.Result(String.valueOf((int) x),
                        String.valueOf(y), String.valueOf(r), AreaChecker.isInArea(x, y, r));
                bean.addResult(result);

                // code for checking script evaluation time
                Duration duration = Duration.between(time, LocalDateTime.now());
                System.out.println("Time elapsed: " + duration + " milliseconds");

                // Respond with JSON
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
            } catch (NumberFormatException e) {
//            ErrorUtil.sendError(response, SC_UNPROCESSABLE_ENTITY, "Invalid number format");
            } catch (Exception e) {
//            e.printStackTrace();
//            ErrorUtil.sendError(response, SC_INTERNAL_SERVER_ERROR, "Internal Server Error");
            }
        }
    }
}