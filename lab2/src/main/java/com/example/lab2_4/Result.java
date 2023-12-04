package com.example.lab2_4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Result {
    ArrayList<HashMap<String, String>> data;
    private Double x;
    private Double y;
    private Double r;
    private String message;
    private String currentTime;
    private long timeResponse;

    public Result(ArrayList<HashMap<String, String>> data) {
        HashMap<String, String> answer = new HashMap<>();
        answer.put("x", String.valueOf(x));
        answer.put("y", String.valueOf(y));
        answer.put("r", String.valueOf(r));
        answer.put("current_time", currentTime);
        answer.put("time_response", String.valueOf(timeResponse));
        answer.put("message",String.valueOf(message));
        data.add(answer);
    }

    public Double getX() {
        return x;
    }

    public ArrayList<HashMap<String, String>> getData() {
        return data;
    }


    public Double getY() {
        return y;
    }


    public Double getR() {
        return r;
    }


    public String getMessage() {
        return message;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public long getTimeResponse() {
        return timeResponse;
    }

    public void setX(Double x) {
        this.x = x;
    }


    public void setY(Double y) {
        this.y = y;
    }


    public void setR(Double r) {
        this.r = r;
    }


    public void setMessage(String message) {
        this.message = this.message;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public void setTimeResponse(long timeResponse) {
        this.timeResponse = timeResponse;
    }


}
