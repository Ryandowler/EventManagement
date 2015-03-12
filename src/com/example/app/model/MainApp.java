package com.example.app.model;
//huhh

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        // add in Event e;
        System.out.println("Enter 'Event' to create a new Event");
        System.out.println("Enter 'Manager' to inser a Manager");
        String option = keyboard.nextLine();
        if(option.equals("Event") || option.equals("event") || option.equals("e"))
        {
 
            int opt;
            do {
                System.out.println("1. Create a new Event");
                System.out.println("2. Delete an existing Event");
                System.out.println("3. Edit an existing Event");
                System.out.println("4. View all Events");
                System.out.println("5. Exit");

                System.out.print("Enter option: ");
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);

                System.out.println("You chose option: " + opt);

                switch (opt) {
                    case 1: {
                        System.out.println("Attempting to Create Event . .");
                        createEvent(keyboard, model);
                        break;
                    }
                    case 2: {
                        System.out.println("Attempting to Delete Event . .");
                        deleteEvent(keyboard, model);
                        break;
                    }
                    case 3: {
                        System.out.println("Attempting to Edit Event" );
                        editEvent(keyboard, model);
                        break;
                    }
                    case 4: {
                        System.out.println("Attempting to View Event");
                        viewEvents(model);
                        break;
                    }
                }
            } while (opt != 5);
            System.out.println("Goodbye");
        }
        // ----------------------------------------------------------------------Manager table----------------------------------------------------------------
        else if(option.equals("Manager") || option.equals("manager") || option.equals("m"))
        {
               int optMan;
            do {
                System.out.println("1. Insert a Manager");
                System.out.println("2. Delete an existing Manager");
                System.out.println("3. Edit an existing Manager");
                System.out.println("4. View all Manager");
                System.out.println("5. Exit");

                System.out.print("Enter option: ");
                String line = keyboard.nextLine();
                optMan = Integer.parseInt(line);

                System.out.println("You chose option: " + optMan);

                switch (optMan) {
                    case 1: {
                        System.out.println("Attempting to insert Manager . .");
                        createEvent(keyboard, model);
                        break;
                    }
                    case 2: {
                        System.out.println("Attempting to Delete Manager . .");
                        deleteEvent(keyboard, model);
                        break;
                    }
                    case 3: {
                        System.out.println("Attempting to Edit Manager" );
                        editEvent(keyboard, model);
                        break;
                    }
                    case 4: {
                        System.out.println("Attempting to View Manager");
                        viewManagers(model);
                        break;
                    }
                }
            }while (optMan != 5);
            System.out.println("Goodbyeee");  
        }
    }//main method end

    //Methods used in above cases
    //Create Event Method
    private static void createEvent(Scanner keyboard, Model model) {
        Event e = readEvent(keyboard);
        if (model.addEvent(e)) {
            System.out.println("Event succesfully added to the database!\n");
        } else {
            System.out.println("Event Not added to the database!\n");
        }
    }

    //Delete Event Method
    private static void deleteEvent(Scanner keyboard, Model model) {
        System.out.println("Deleting Event");
        System.out.println("Enter the the ID of the Event you would like to Delete");
        int eventID = Integer.parseInt(keyboard.nextLine());
        Event e;
        e = model.findEventByThe_eID(eventID);
        if (e != null) {
            if (model.removeEvent(e)) {
                System.out.println("Event Deleted");
            } else {
                System.out.println("Event not Deleted");
            }
        } else {
            System.out.println("Event not found");
        }
    }

    //edit an event
    private static void editEvent(Scanner kb, Model model) {
        System.out.println("Enter the the ID of the Event you would like to Edit");
        int eventID = Integer.parseInt(kb.nextLine());
        Event e;

        e = model.findEventByThe_eID(eventID);
        if (e != null) {
            editEventDetails(kb, e);
            if (model.updateEvent(e)) {
                System.out.println("Event Updated\n");
            } else {
                System.out.println("Event not Updated\n");
            }
        } else {
            System.out.println("Event not found!!\n");
        }

    }

    private static void viewEvents(Model mdl) {
        List<Event> events = mdl.getEvents();
        System.out.println();
        if (events.isEmpty()) {
            System.out.println("There are no events to see");
        }
        else{
            System.out.printf("%7s %40s %50s %20s %20s %20s %20s %9s\n", "eventID", "Title", "description", "startDate", "endDate", "time", "maxAttendees", "cost", "manager");
            for (Event ev : events) {
                System.out.printf("%7d %40s %50s %20s %20s %20s %20d %9.2f %7s\n",
                        ev.getEventID(),
                        ev.getTitle(),
                        ev.getDescription(),
                        ev.getStartDate(),
                        ev.getEndDate(),
                        ev.getTime(),
                        ev.getMaxAttendees(),
                        ev.getCost(),
                        ev.getManagerID()
                );
            }
        }
        //make a line of white space
        System.out.println();
    }

    //Utility Methods used by main methods ^^
    private static Event readEvent(Scanner keyb) {
        String title, description, startDate, endDate, time;
        int maxAttendees, managerID;
        double cost;
        String line; //buffer variable 

        title = getString(keyb, "Enter the Title: ");
        description = getString(keyb, "Describe the event: ");
        startDate = getString(keyb, "When date does it start? ");
        endDate = getString(keyb, "When date does it End? ");
        time = getString(keyb, "What time does it start? ");
        line = getString(keyb, "what is the maximum amount of attendees? ");
        maxAttendees = Integer.parseInt(line);
        line = getString(keyb, "what is the price for this event? ");
        cost = Double.parseDouble(line);
        line = getString(keyb, "Enter the manager ID (enter -1 for no manager):");
        managerID= Integer.parseInt(line);

        Event e
                = new Event(title, description, startDate, endDate, time, maxAttendees, cost, managerID);

        return e;
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

    private static void editEventDetails(Scanner kb, Event e) {
        String title, desription, startDate, endDate, time;
        int maxAttendees, managerID;
        double cost;
        String box1, box2, box3;

        title = getString(kb, "Enter the Title[" + e.getTitle() + "] ");
        desription = getString(kb, "Enter the desription[" + e.getDescription() + "] ");
        startDate = getString(kb, "Enter the Start Date[" + e.getStartDate() + "] ");
        endDate = getString(kb, "Enter the End Date[" + e.getEndDate() + "] ");
        time = getString(kb, "Enter the Time[" + e.getTime() + "] ");
        box1 = getString(kb, "Enter the Max Attendees[" + e.getMaxAttendees() + "] ");
        box2 = getString(kb, "Enter the cost[" + e.getCost() + "] ");
        box3 = getString(kb, "Enter the manager ID[" + e.getManagerID() + "] ");

        //if field is not empty the entered data becomes the new titl/description
        if (title.length() != 0) {
            e.setTitle(title);
        }
        if (desription.length() != 0) {
            e.setDescription(desription);
        }
        if (startDate.length() != 0) {
            e.setStartDate(startDate);
        }
        if (endDate.length() != 0) {
            e.setEndDate(endDate);
        }
        if (time.length() != 0) {
            e.setTime(time);
        }
        if (box1.length() != 0) {
            maxAttendees = Integer.parseInt(box1);
            e.setMaxAttendees(maxAttendees);
        }
        if (box2.length() != 0) {
            cost = Double.parseDouble(box2);
            e.setCost(cost);
        }
        if (box3.length() != 0) {
            managerID = Integer.parseInt(box3);
            e.setManagerID(managerID);
        }

    }
    // MANAGERS TABLE--------------------------------------------
        private static void viewManagers(Model mdl) {
        List<Manager> managers = mdl.getManagers();
        System.out.println();
        if (managers.isEmpty()) {
            System.out.println("There are no Managers to see");
        }
        else{
            System.out.printf("%7s %20s\n", "eventID", "name");
            for (Manager ma : managers) {
                System.out.printf("%7d %20s\n",
                        ma.getManagerID(),
                        ma.getName()
                );
            }
        }
        //make a line of white space
        System.out.println();
    }

}
