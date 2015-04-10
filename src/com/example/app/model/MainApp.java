package com.example.app.model;
//huhh

import com.example.app.ManagerEmailComparator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
//updated
public class MainApp {

    private static final int NAME_ORDER = 1;
    private static final int EMAIL_ORDER = 2;

    //Sets Up User Interface Options:
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        String option = null;
        try {
            do {
                System.out.println("EVENT MANAGEMENT COMPANY APP");
                System.out.println("---------------------");
                System.out.println();
                System.out.println("-1 EVENT TABLE");
                System.out.println("-2 MANAGER TABLE");
                System.out.println("-3 EXIT APP");

                System.out.println("-Choose A Table: ");
                option = keyboard.nextLine();
                if (option.equals("Event") || option.equals("event") || option.equals("e") || option.equals("1")) {
                    doEventMenu(keyboard, model);
                } else if (option.equals("Manager") || option.equals("Manager") || option.equals("m") || option.equals("2")) {
                    doManagerMenu(keyboard, model);
                }
            } while (!(option.equals("Exit") || option.equals("exit") || option.equals("ex") || option.equals("3")));
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");

        }

    }

    //Methods used in above cases
    //Create Event Method
    private static void createEvent(Scanner keyboard, Model model) {
        try {
            Event e = readEvent(keyboard);
            if (model.addEvent(e)) {
                System.out.println("");
                System.out.println("Event succesfully added to the database!\n");
            } else {
                System.out.println("Event Not added to the database!\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }
    }

    //Delete Event Method
    private static void deleteEvent(Scanner keyboard, Model model) {
        try {
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
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }
    }

    //edit an event
    private static void editEvent(Scanner kb, Model model) {
        try {
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
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }

    }

    /* private static void viewEvents(Model mdl) {
     List<Event> events = mdl.getEvents();
     System.out.println();
     if (events.isEmpty()) {
     System.out.println("There are no events to see");
     } else {
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
     }*/
    //Code For Viewing All events:
    private static void viewEvents(Model model) {
        List<Event> events = model.getEvents();
        System.out.println();
        if (events.isEmpty()) {
            System.out.println("-There Are No Events In The Database.");
        } else {
            displayEvents(events, model);
        }
        System.out.println();
    }

    //Display Buses:
    private static void displayEvents(List<Event> events, Model model) {
        System.out.printf("%7s %40s %50s %20s %20s %20s %20s %9s %20s\n",
                "EventID",
                "Title",
                "Description",
                "Start Date",
                "EndDate",
                "Time",
                "Max Attendees",
                "Cost",
                "Manager Assigned");
        for (Event ev : events) {
            Manager m = model.findManagerById(ev.getManagerID());
            System.out.printf("%7d %40s %50s %20s %20s %20s %20d %9.2f %20s\n",
                    ev.getEventID(),
                    ev.getTitle(),
                    ev.getDescription(),
                    ev.getStartDate(),
                    ev.getEndDate(),
                    ev.getTime(),
                    ev.getMaxAttendees(),
                    ev.getCost(),
                    (m != null) ? m.getName() : "--NO MANAGER--");

        }
    }

    //Utility Methods used by main methods ^^
    private static Event readEvent(Scanner keyb) {

        String title, description, startDate, endDate, time;
        int maxAttendees, managerID;
        double cost;
        String line, line2, line3; //buffer variable 
        System.out.println();
        title = getString(keyb, "Enter the Title: ");
        description = getString(keyb, "Describe the event: ");
        startDate = getString(keyb, "When date does it start? ");
        endDate = getString(keyb, "When date does it End? ");
        time = getString(keyb, "What time does it start? ");
        line = getString(keyb, "what is the maximum amount of attendees? ");
        maxAttendees = Integer.parseInt(line);
        line2 = getString(keyb, "what is the price for this event? ");
        cost = Double.parseDouble(line2);
        line3 = getString(keyb, "Enter the manager ID (enter -1 for no manager):");
        managerID = Integer.parseInt(line3);
        System.out.println();

        Event e
                = new Event(title, description, startDate, endDate, time, maxAttendees, cost, managerID);

        return e;

    }

    /*
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

     }*/
    //view a single event
    private static void viewEvent(Scanner keyboard, Model model) {
        try {

            System.out.println("Enter the the ID of the Event you would like to View");
            int eventID = Integer.parseInt(keyboard.nextLine());
            Event e;
            e = model.findEventByThe_eID(eventID);
            if (e != null) {
                Manager m = model.findManagerById(e.getManagerID());
                System.out.println();
                System.out.println("Tile          : " + e.getTitle());
                System.out.println("Description   : " + e.getDescription());
                System.out.println("StartDate     : " + e.getStartDate());
                System.out.println("End Date      : " + e.getEndDate());
                System.out.println("Time          : " + e.getTime());
                System.out.println("Max Attendee  : " + e.getMaxAttendees());
                System.out.println("Cost          : " + e.getCost());
                System.out.println("Manager       : " + ((m != null) ? m.getName() : "--NO MANAGER--"));
                System.out.println();

            } else {
                System.out.println("Event not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }

    }

    //Edit code (This Code Gets String From Keyboard And Places Current Info And Placement Reads New Value):
    private static void editEventDetails(Scanner keyb, Event e) {
        try {
            String title, desription, startDate, endDate, time;
            int maxAttendees, managerID;
            double cost;
            String line1, line2, line3;

            title = getString(keyb, "-Enter title [" + e.getTitle() + "]: ");
            desription = getString(keyb, "-Enter desription [" + e.getDescription() + "]: ");
            startDate = getString(keyb, "-Enter start Date [" + e.getStartDate() + "]: ");
            endDate = getString(keyb, "-Enter EndDate [" + e.getEndDate() + "]: ");
            time = getString(keyb, "-Enter time[" + e.getTime() + "]: ");
            line1 = getString(keyb, "-Enter maxAttendees[" + e.getMaxAttendees() + "]: ");
            line2 = getString(keyb, "Enter cost[" + e.getCost() + "]: ");
            line3 = getString(keyb, "Enter managerID[" + e.getManagerID() + "]: ");

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
            if (line1.length() != 0) {
                maxAttendees = Integer.parseInt(line1);
                e.setMaxAttendees(maxAttendees);
            }
            if (line2.length() != 0) {
                cost = Integer.parseInt(line2);
                e.setCost(cost);
            }
            if (line3.length() != 0) {
                managerID = Integer.parseInt(line3);
                e.setManagerID(managerID);
            }
        } catch (NumberFormatException e1) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e1.getMessage() + ".\n");
        }
    }

    //--------------------------------------MANAGERS TABLE--------------------------------------------
    //---------------------------------CREATE MANAGERS ------------------------------  
    private static void createManager(Scanner keyboard, Model model) {
        try {

            Manager m = readManager(keyboard);
            if (model.addManager(m)) {
                System.out.println("Manager succesfully added to the database!\n");
            } else {
                System.out.println("Manager Not added to the database!\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }

    }//end of CreateManager

    //---------------------------------DELETE MANAGERS ------------------------------  
    private static void deleteManager(Scanner keyboard, Model model) {
        try {
            System.out.println("Deleting Manager");
            System.out.println("Enter the the ID of the Manager you would like to Delete");
            int managerID = Integer.parseInt(keyboard.nextLine());
            Manager m;
            m = model.findManagerByThe_mID(managerID);
            if (m != null) {
                if (model.removeManager(m)) {
                    System.out.println("Manager Deleted");
                } else {
                    System.out.println("Manager not Deleted");
                }
            } else {
                System.out.println("Manager not found");
            }
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }
    }

    //------------------------------edit an event---------------------------
    private static void editManager(Scanner kb, Model model) {
        try {
            System.out.println("Enter the the ID of the Manager you would like to Edit");
            int managerID = Integer.parseInt(kb.nextLine());
            Manager m;

            m = model.findManagerByThe_mID(managerID);
            if (m != null) {
                editManagerDetails(kb, m);
                if (model.updateManager(m)) {
                    System.out.println("Manager Updated\n");
                } else {
                    System.out.println("Manager not Updated\n");
                }
            } else {
                System.out.println("Manager not found!!\n");
            }
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }

    }

    //---------------------------------VIEW MANAGERS ------------------------------  
    private static void viewManagers(Model mdl, int order) {
        List<Manager> managers = mdl.getManagers();
        System.out.println();
        if (managers.isEmpty()) {
            System.out.println("There are no Managers to see");
        } else {
            if (order == NAME_ORDER) {
                Collections.sort(managers);
                }
            else if (order == EMAIL_ORDER){
                     Comparator<Manager> cmptr = new ManagerEmailComparator();
                     Collections.sort(managers, cmptr);
                    }

            
            Collections.sort(managers);
            System.out.printf("%7s %20s %15s\n", "managerID", "name", "managerEmail");
            for (Manager ma : managers) {
                System.out.printf("%7d %20s %30s\n",
                        ma.getManagerID(),
                        ma.getName(),
                        ma.getManagerEmail()
                );
            }
        }
        //make a line of white space
        System.out.println();
    }

    //view single manager
    private static void viewManager(Scanner keyboard, Model model) {
        System.out.println("Enter the the ID of the Manager you would like to View");
        int managerID = Integer.parseInt(keyboard.nextLine());
        Manager m;
        m = model.findManagerByThe_mID(managerID);
        if (m != null) {
            System.out.println();
            System.out.println("Name          : " + m.getName());
            System.out.println("Email         : " + m.getManagerEmail());
            System.out.println();

            List<Event> eventList = model.getEventsBymanagerID(m.getManagerID());
            if (eventList.isEmpty()) {
                System.out.println();
                System.out.print("This manager is not assigned to any Events right now");
                System.out.println();
            } else {
                System.out.println();
                System.out.print("This manager is assigned to the following Events: ");
                System.out.println();
                displayEvents(eventList, model);

            }

        } else {
            System.out.println("Manager not found");
        }
    }

    //---------------------------------VIEW MANAGERS END------------------------------
    //---------------------------------READ MANAGERS ------------------------------  
    //Utility Methods used by main methods ^^
    private static Manager readManager(Scanner keyb) {
        String name, managerEmail;

        name = getString(keyb, "Enter Managers Name: ");
        managerEmail = getString(keyb, "Enter Managers email: ");

        Manager m
                = new Manager(name, managerEmail);

        return m;
    }
    //---------------------------------READ MANAGERS END------------------------------

    private static void editManagerDetails(Scanner kb, Manager m) {
        String name, managerEmail;

        name = getString(kb, "Enter the name[" + m.getName() + "] ");
        managerEmail = getString(kb, "Enter the Email[" + m.getManagerEmail() + "] ");

        //if field is not empty the entered data becomes the new titl/description
        if (name.length() != 0) {
            m.setName(name);
        }
        if (managerEmail.length() != 0) {
            m.setManagerEmail(managerEmail);
        }

    }

    //input and exception
    private static int getInt(Scanner keyboard, String prompt) {
        int opt = 0;
        boolean finished = false;

        do {
            try {
                System.out.println(prompt);
                String line = keyboard.nextLine();
                opt = Integer.parseInt(line);
                finished = true;
            } catch (NumberFormatException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        } while (!finished);
        return opt;
    }

    private static void doEventMenu(Scanner keyboard, Model model) {
        try {

            int opt;

            // Do/While Loop For Possible Options, As Long As 5 Is Not Entered It Will Continue To Run:
            do {
                System.out.println();
                System.out.println("-EVENT TABLE");
                System.out.println("----------");
                System.out.println();
                System.out.println("-1 Create A New Event.");
                System.out.println("-2 Delete Existing Event.");
                System.out.println("-3 Edit Existing Event.");
                System.out.println("-4 View All Events.");
                System.out.println("-5 View Single Event.");
                System.out.println("-6 Back To Tables.");

                opt = getInt(keyboard, "Enter option");

                //If Options Is CLicked Then Break:
                System.out.println("-You Chose Option: " + opt);
                switch (opt) {
                    //To Create A New Event:
                    case 1: {
                        System.out.println("-Creating A New Event.");
                        createEvent(keyboard, model);
                        break;
                    }
                    //To Delete A Existing Event:
                    case 2: {
                        System.out.println("-Deleting A Event.");
                        deleteEvent(keyboard, model);
                        break;
                    }
                    //To Update A Existing Event:
                    case 3: {
                        System.out.println("-Updating A Event.");
                        editEvent(keyboard, model);
                        break;
                    }
                    //To View All Event:
                    case 4: {
                        System.out.println("-Viewing All Event.");
                        viewEvents(model);
                        break;
                    }
                    //To View All Event:
                    case 5: {
                        System.out.println("-Viewing Single Event.");
                        viewEvent(keyboard, model);
                        break;
                    }
                }
            } //Once Not Equals To 5 Programes Runs Else Stops:
            while (opt != 6);

        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }

    }

    private static void doManagerMenu(Scanner keyboard, Model model) {
        try {

            int opt;

            // Do/While Loop For Possible Options, As Long As 5 Is Not Entered It Will Continue To Run:
            do {
                System.out.println();
                System.out.println("-MANAGER TABLE");
                System.out.println("----------");
                System.out.println();
                System.out.println("-1 Create A New Manager.");
                System.out.println("-2 Delete Existing Manager.");
                System.out.println("-3 Edit Existing Manager.");
                System.out.println("-4 View All Managers.");
                System.out.println("-5 View All Managers by Name.");
                System.out.println("-6 View Single Managers.");
                System.out.println("-7 Back To Tables.");

                opt = getInt(keyboard, "Enter option");

                //If Options Is CLicked Then Break:
                System.out.println("-You Chose Option: " + opt);
                switch (opt) {
                    //To Create A New Manager:
                    case 1: {
                        System.out.println("-Creating A New Manager.");
                        createManager(keyboard, model);
                        break;
                    }
                    //To Delete A Existing Manager:
                    case 2: {
                        System.out.println("-Deleting A Manager.");
                        deleteManager(keyboard, model);
                        break;
                    }
                    //To Update A Existing Manager:
                    case 3: {
                        System.out.println("-Updating A Manager.");
                        editManager(keyboard, model);
                        break;
                    }
                    //To View All Managers:
                    case 4: {
                        System.out.println("-Viewing All Manager.");
                        viewManagers(model, NAME_ORDER);
                        break;
                    }
                    case 5: {
                        System.out.println("-Viewing All Managers by Name.");
                        viewManagers(model, EMAIL_ORDER);
                        break;
                    }
                    case 6: {
                        System.out.println("-Viewing Single Manager.");
                        viewManager(keyboard, model);
                        break;
                    }
                }
            } //Once Not Equals To 5 Programes Runs Else Stops:
            while (opt != 7);
        } catch (NumberFormatException e) {
            System.out.println("-Incorrect Data Type Or Null Input.");
            System.out.println("Number Format Exception: " + e.getMessage() + ".\n");
        }
    }

    private static String getString(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        return keyboard.nextLine();
    }

}
