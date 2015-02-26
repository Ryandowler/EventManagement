package com.example.app.model;

public class Manager {

    private int managerID;
    private String name;


    public Manager(int managerID, String n) {
        this.managerID = managerID;
        this.name = n;
    
    }

    public Manager(String n) {
        this(-1, n);
    }

    public int getManagerID() {
        return managerID;
    }

    public String getName() {
        return name;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public void setName(String name) {
        this.name = name;
    }
}