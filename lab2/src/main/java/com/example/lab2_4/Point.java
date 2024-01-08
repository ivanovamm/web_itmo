package com.example.lab2_4;

import java.time.LocalDateTime;
import java.util.Objects;

public class Point {
    private final double x;
    private final double y;
    private final double r;

    private final boolean isInArea;
    private final String time;

    public Point(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.isInArea = isInside(x, y, r);
        String h;
        String m;
        String s;
        if (LocalDateTime.now().getHour() < 10) {
            h = "0" + LocalDateTime.now().getHour();
        } else h = String.valueOf(LocalDateTime.now().getHour());
        if (LocalDateTime.now().getMinute() < 10) {
            m = "0" + LocalDateTime.now().getMinute();
        } else m = String.valueOf(LocalDateTime.now().getMinute());
        if (LocalDateTime.now().getSecond() < 10) {
            s = "0" + LocalDateTime.now().getSecond();
        } else s = String.valueOf(LocalDateTime.now().getSecond());
        this.time = h + ":" + m + ":" + s;
    }

    private boolean isInside(double x, double y, double r) {
        if (x >= 0 && y >= 0) {
            return (x <= r) && (y <= r / 2);
        }
        if (x < 0 && y >= 0) {
            return (x * x + y * y) <= (r/2 * r/2);
        }
        if (x <= 0 && y <= 0) {
            return (x >= -r) && (-y <= -r / 2 + x / 2) && (y >= -r / 2);
        }
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

    public String getTime() {
        return time;
    }

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
