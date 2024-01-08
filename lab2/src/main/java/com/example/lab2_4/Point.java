package com.example.lab2_4;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Point {
    private final double x;
    private final double y;
    private final double r;

    private final boolean isInArea;
    private String time;

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInArea = isInside(x, y, r);
        this.time = LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute()+":"+LocalDateTime.now().getSecond();
    }

    private boolean isInside(double x, double y, double r) {
        // Треугольник в 1 четверти
        if (x >= 0 && y >= 0) {
            return (x <= r) && (y <= r / 2);
        }
        // Прямоугольник во 2 четверти
        if (x < 0 && y >= 0) {
            return (x >= -r / 2) && (y <= r);
        }
        // Сектор в 3 четверти
        if (x <= 0 && y <= 0) {
            return (x * x + y * y) <= (r * r);
        }
        // 4 четверть
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getR() {
        return r;
    }

    public boolean isInArea() {
        return isInArea;
    }

    public String getTime(){return time;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return y == point.y && Double.compare(x, point.x) == 0 && r == point.r;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, r);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", isInArea=" + isInArea +
                '}';
    }
}
