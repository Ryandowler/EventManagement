package com.example.app.model;
public class Event implements Comparable<Event> {
   //variables
    private int eventID;
    private String title;
    private String description;
    private String startDate;
    private String endDate;
    private String time;
    private int maxAttendees;
    private double cost;
    private int managerID;
//creating the constructor, needed as my above variables are private, so this is how i access them 1
    public Event(int eID, String t, String d, String sd, String ed, String tm, int ma, double c, int mID) {
        this.eventID = eID;
        this.title = t;
        this.description = d;
        this.startDate = sd;
        this.endDate = ed;
        this.time = tm;
        this.maxAttendees = ma;
        this.cost = c;
        this.managerID = mID;
    }
    public Event(String t, String d, String sd, String ed, String tm, int ma, double c, int mID) {
        this(-1, t, d, sd, ed, tm, ma, c, mID);
    }
    //get and sets
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
    public int getManagerID() {
        return managerID;
    }
    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }
    @Override
    public int compareTo(Event that) {
      String myTitle = this.getTitle();
      String yourTitle = that.getTitle();
      
      return  myTitle.compareTo(yourTitle);
    }
}


    
    
    
    
    
    
    
    
    /*
    
    
    
    //Edit Code If/Else If Table Is Updated Or Not:
    private static void editBus(Scanner kb, Model m) {
        try {
            System.out.print("-Enter The Bus ID You Want To Edit: ");
            int busID = Integer.parseInt(kb.nextLine());
            Bus b;
 
            b = m.findBusByBusID(busID);
            if (b != null) {
                editBusDetails(kb, b);
                if (m.updateBus(b)) {
                    System.out.println("-Bus Updated.\n");
                } else {
                    System.out.println("-Bus Not Updated.\n");
                }
            } else {
                System.out.println("-Bus Not Found.\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }
    }
    ####################################


 //Code Add Garage:
    public boolean addGarage(Garage g) {
        boolean result = false;
        try {
            int garageID = this.garageGateway.insertGarage(g.getGarageName(), g.getGarageAddress(), g.getGaragePhoneNo(), g.getManagerName());
            if (garageID != -1) {
                g.setGarageID(garageID);
                this.garages.add(g);
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    

*/

    
    
    
    
    
    
    
    
    
    
    
    
    