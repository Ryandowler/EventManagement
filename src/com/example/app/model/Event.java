package com.example.app.model;

public class Event {

    private int eventID;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String time;
    private int maxAttendees;
    private double cost;

    //creating the constructor, needed as my above variables are private, so this is how i access them
    public Event(int eID, String t, String d, String sd, String ed, String tm, int ma, double c) {
        this.eventID = eID;
        this.title = t;
        this.description = d;
        this.startDate = sd;
        this.endDate = ed;
        this.time = tm;
        this.maxAttendees = ma;
        this.cost = c;
    }

    public Event(String t, String d, String sd, String ed, String tm, int ma, double c) {
        this(-1, t, d, sd, ed, tm, ma, c);
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMaxAttendees() {
        return maxAttendees;
    }

    public void setMaxAttendees(int maxAttendees) {
        this.maxAttendees = maxAttendees;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}
