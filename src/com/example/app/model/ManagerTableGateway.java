package com.example.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ManagerTableGateway {

    private Connection mConnection;
    

    private static final String TABLE_NAME = "managers";
    private static final String COLUMN_ID = "managerID";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_MANAGER_EMAIL = "managerEmail";
    
    

    public ManagerTableGateway(Connection connection) {
        mConnection = connection;
    }
      public List<Manager> getManagers() throws SQLException {
        String query;                   // the SQL query to execute
        Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
        ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query
        List<Manager> managers;         // the java.util.List containing the Manager objects created for each row
                                        // in the result of the query the id of a manager

        //String name;
       // int managerID;
        Manager m;                   // a Manager object created from a row in the result of the query

        // execute an SQL SELECT statement to get a java.util.ResultSet representing
        // the results of the SELECT statement
        query = "SELECT * FROM " + TABLE_NAME;
        stmt = this.mConnection.createStatement();
        rs = stmt.executeQuery(query);

        // iterate through the result set, extracting the data from each row
        // and storing it in a Manager object, which is inserted into an initially
        // empty ArrayList
        managers = new ArrayList<Manager>();
        while (rs.next()) {
            int managerID = rs.getInt(COLUMN_ID);
            String name = rs.getString(COLUMN_NAME);
            String managerEmail = rs.getString(COLUMN_MANAGER_EMAIL);
            

            m = new Manager(managerID, name, managerEmail);
            managers.add(m);
        }

        // return the list of Manager objects retrieved
        return managers;
    }

    public int insertManager(String n, String me) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;
        int managerID = -1;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_NAME + ", "
                + COLUMN_MANAGER_EMAIL
                + ") VALUES (?, ?)";

        // create a PreparedStatement object to execute the query and insert the values into the query
        stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, n);
        stmt.setString(2, me);
    

        // execute the query and make sure that one and only one row was inserted into the database
        numRowsAffected = stmt.executeUpdate();
        if (numRowsAffected == 1) {
            // if one row was inserted, retrieve the id assigned to that row
            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();

            managerID = keys.getInt(1);
        }

        // return the id assigned to the row in the database
        return managerID;
    }

    public boolean deleteManager(int managerID) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // the required SQL DELETE statement with place holders for the id of the row to be remove from the database
        query = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";

        // create a PreparedStatement object to execute the query and insert the id into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setInt(1, managerID);
        numRowsAffected = stmt.executeUpdate();

        // execute the query
       // numRowsAffected = stmt.executeUpdate();

        // return the true if one and only one row was deleted from the database
        return (numRowsAffected == 1);
    }
    boolean updateManager(Manager m) throws SQLException {
        String query;                   // the SQL query to execute
        PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
        int numRowsAffected;

        // the required SQL INSERT statement with place holders for the values to be inserted into the database
        query = "UPDATE " + TABLE_NAME + " SET "
                + COLUMN_NAME + " = ?, "
                + COLUMN_MANAGER_EMAIL + " = ? "
                + " WHERE " + COLUMN_ID + " = ? ";

        // create a PreparedStatement object to execute the query and insert the new values into the query
        stmt = mConnection.prepareStatement(query);
        stmt.setString(1, m.getName());
        stmt.setString(2, m.getManagerEmail());
        stmt.setInt(3, m.getManagerID());
        // execute the query
        numRowsAffected = stmt.executeUpdate();

        // return the true if one and only one row was updated in the database
        return (numRowsAffected == 1);
    }

}