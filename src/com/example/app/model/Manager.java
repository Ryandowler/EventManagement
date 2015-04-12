package com.example.app.model;
public class Manager implements Comparable<Manager>{
    private int managerID;
    private String name;
    private String managerEmail;
    public Manager(int managerID, String n, String me) {
        this.managerID = managerID;
        this.name = n;
        this.managerEmail = me;
    }
    public Manager(String n, String me) {
        this(-1, n, me);
    }
    public int getManagerID() {
        return managerID;
    }
    public String getName() {
        return name;
    }
    public String getManagerEmail() {
        return managerEmail;
    }
    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }
    @Override
    //make my objects compareable
    public int compareTo(Manager that) {
        String myName = this.getName();
        String yourName = that.getName();   
      return  myName.compareTo(yourName);
    }
}