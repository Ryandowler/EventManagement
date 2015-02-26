package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EventTableGateway {

    private Connection mConnection;

    private static final String TABLE_NAME = "event";
    private static final String COLUMN_ID = "eventID";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_START_DATE = "startDate";
    private static final String COLUMN_END_DATE = "endDate";
    private static final String COLUMN_TIME = "time";
    private static final String COLUMN_MAX_ATTENDEES = "maxAttendees";
    private static final String COLUMN_COST = "cost";
    private static final String COLUMN_MANAGER_ID = "managerID";

    public EventTableGateway(Connection connection) {
        mConnection = connection;
    }

    public List<Event> getEvents() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Event> events;             // the java.util.List containing the Event objects created for each row
                                         // in the result of the query the id of a event

       // String title, description, startDate, endDate;
       // int eventID, maxAttendees, managerID;
       // double cost;
        
       
        Event e;                   // a Event object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Event object, which is inserted into an initially
        // empty ArrayList
        events = new ArrayList<Event>();
        while (rs.next()) {
            int eID = rs.getInt(COLUMN_ID);
            String title = rs.getString(COLUMN_TITLE);
            String description = rs.getString(COLUMN_DESCRIPTION);
            String startDate = rs.getString(COLUMN_START_DATE);
            String endDate = rs.getString(COLUMN_END_DATE);
            String time = rs.getString(COLUMN_TIME);
            int maxAttendees = rs.getInt(COLUMN_MAX_ATTENDEES);
            double cost = rs.getDouble(COLUMN_COST);
            int managerID = rs.getInt(COLUMN_MANAGER_ID);
            if (rs.wasNull()) {
                managerID = -1;
            }

            e = new Event(eID, title, description, startDate, endDate, time, maxAttendees, cost, managerID);
            events.add(e);
        }
        return events;
    }

    public int insertEvent(String t, String d, String sd, String ed, String tm, int ma, double c, int mID) throws SQLException {

        String query;       // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int eID = -1;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_TITLE + ", "
                + COLUMN_DESCRIPTION + ", "
                + COLUMN_START_DATE + ", "
                + COLUMN_END_DATE + ", "
                + COLUMN_TIME + ", "
                + COLUMN_MAX_ATTENDEES + ", "
                + COLUMN_COST + ", "
                + COLUMN_MANAGER_ID
                + ") VALUES (?, ?, ?, ?, ?, ?, ?,?)";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, t);
        stmt.setString(2, d);
        stmt.setString(3, sd);
        stmt.setString(4, ed);
        stmt.setString(5, tm);
        stmt.setInt(6, ma);
        stmt.setDouble(7, c);
        //stmt.setInt(8, mID);
        if (mID == -1) {
            stmt.setNull(8, java.sql.Types.INTEGER);
        }
        else {
            stmt.setInt(8, mID);
        }

        //  execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row and create a Event object to return
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            eID = keys.getInt(1);
        }

        // return the Event object created or null if there was a problem
        return eID;
    }

    public boolean deleteEvent(int ID) throws SQLException {
        String query;
        PreparedStatement stmt;
        int numRowsAffected = 0;

        query = " DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";

        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, ID);
        numRowsAffected = stmt.executeUpdate();

        return (numRowsAffected == 1);
    }

   //undelete
    boolean updateEvent(Event e) throws SQLException {

        String query;       // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int mID = -1;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET "
                + COLUMN_TITLE + " = ?, "
                + COLUMN_DESCRIPTION + " = ?, "
                + COLUMN_START_DATE + " = ?, "
                + COLUMN_END_DATE + " = ?, "
                + COLUMN_TIME + " = ?, "
                + COLUMN_MAX_ATTENDEES + " = ?, "
                + COLUMN_COST + " = ?, "
                + COLUMN_MANAGER_ID + " = ? "
                + " WHERE " + COLUMN_ID + " = ? ";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, e.getTitle());
        stmt.setString(2, e.getDescription());
        stmt.setString(3, e.getStartDate());
        stmt.setString(4, e.getEndDate());
        stmt.setString(5, e.getTime());
        stmt.setInt(6, e.getMaxAttendees());
        stmt.setDouble(7, e.getCost());
        mID = e.getManagerID();
        if (mID == -1) {
            stmt.setNull(8, java.sql.Types.INTEGER);
        }
        else {
            stmt.setInt(8, mID);
        }
        stmt.setInt(9, e.getManagerID());


        //  execute the query 
        numRowsAffected = stmt.executeUpdate();

        //  return  the true if one row was updated  in the DB
        return (numRowsAffected == 1);
    }

}
