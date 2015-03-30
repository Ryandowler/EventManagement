package com.example.app.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
    List<Manager> managers;
    EventTableGateway eventGateway;
    ManagerTableGateway managerGateway;

    // connecting to DB
    private Model() {
        try {   //conn = connection object
            Connection conn = DBConnection.getInstance();
            //conn (connection object) being used as a parametor 
            this.eventGateway = new EventTableGateway(conn);
            this.managerGateway = new ManagerTableGateway(conn);

            this.events = this.eventGateway.getEvents();
            this.managers = this.managerGateway.getManagers();
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
            int eventID = this.eventGateway.insertEvent(e.getTitle(), e.getDescription(), e.getStartDate(), e.getEndDate(), e.getTime(), e.getMaxAttendees(), e.getCost(), e.getManagerID());

            if (eventID != -1) {
                e.setEventID(eventID);
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
    //public List<Event> getEvents() {
    //    return this.events;
    // }
    //Get Bus List Code:
    public List<Event> getEvents() {
        try {
            this.events = this.eventGateway.getEvents();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.events;
    }

    public List<Event> getEventsBymanagerID(int managerID) {
        List<Event> list = new ArrayList<Event>();
        for (Event e : this.events) {
            if (e.getManagerID() == managerID) {
                list.add(e);
            }
        }
        return list;
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

    //MANAGER TABLE----------------------------------------#
    //array of managers
    //public List<Manager> getManagers() {
    //     return this.managers;
    //}
    public List<Manager> getManagers() {
        try {
            this.managers = this.managerGateway.getManagers();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.managers;
    }

    Manager findManagerByThe_mID(int mID) {
        Manager m = null;
        int i = 0;
        boolean found = false;
        while (i < this.managers.size() && !found) {
            m = this.managers.get(i);
            if (m.getManagerID() == mID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            m = null;
        }
        return m;
    }

    public boolean addManager(Manager m) {
        boolean result = false;
        try {
            int managerID = this.managerGateway.insertManager(m.getName(), m.getManagerEmail());
            if (managerID != -1) {
                m.setManagerID(managerID);
                this.managers.add(m);
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public boolean removeManager(Manager m) {
        boolean removed = false;

        try {
            removed = this.managerGateway.deleteManager(m.getManagerID());
            /*if (removed) {
             removed = this.managers.remove(m);
             }             */
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return removed;
    }

    Manager findManagerById(int managerID) {
        Manager m = null;
        int i = 0;
        boolean found = false;
        while (i < this.managers.size() && !found) {
            m = this.managers.get(i);
            if (m.getManagerID() == managerID) {
                found = true;
            } else {
                i++;
            }
        }
        if (!found) {
            m = null;
        }
        return m;
    }

    boolean updateManager(Manager m) {
        boolean updated = false;

        try {
            updated = this.managerGateway.updateManager(m);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

        return updated;
    }

}
