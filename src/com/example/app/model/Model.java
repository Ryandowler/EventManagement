package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Model {

    private static Model instance = null;

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        //if i call again this will just return instance, can only have one
        return instance;
    }

    List<Event> events;
    EventTableGateway eventGateway;

    // connecting to DB

    private Model() {
        try {   //conn = connection object
            Connection conn = DBConnection.getInstance();
            //conn (connection object) being used as a parametor 
            this.eventGateway = new EventTableGateway(conn);
            this.events = this.eventGateway.getEvents();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //last was working here 44:20 on video, need to add in the trys and catchs
    public boolean addEvent(Event e) {
        boolean successful = false;
        try {
            int eID = this.eventGateway.insertEvent(e.getTitle(), e.getDescription(), e.getStartDate(), e.getEndDate(), e.getTime(), e.getMaxAttendees(), e.getCost());
            if (eID != -1) {
                e.setEventID(eID);
                this.events.add(e);
                successful = true;
            }
            //catch will not work, "never trown" ??
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return successful;
    }

    public boolean removeEvent(Event e) {
        boolean removed = false;

        try {
            removed = this.eventGateway.deleteEvent(e.getEventID());
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }

    //leave underneth
    public List<Event> getEvents() {
        return this.events;
    }

    Event findEventByThe_eID(int eID) {
        Event e = null;
        int i = 0;
        boolean found = false;
        while (i < this.events.size() && !found) {
            e = this.events.get(i);
            if (e.getEventID() == eID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            e = null;
        }
        return e;
    }

    boolean updateEvent(Event e) {
        boolean updated = false;
        try {
            updated = this.eventGateway.updateEvent(e);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updated;
    }

}
