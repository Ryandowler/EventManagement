package com.example.app.model;
//huhh

import java.util.List;
import java.util.Scanner;

public class MainApp {

    //Sets Up User Interface Options:
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        Model model = Model.getInstance();
        String option = null;
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
    }

    //Methods used in above cases
    //Create Event Method
   
   
            private static void createEvent(Scanner keyboard, Model model)  {
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
        managerID = Integer.parseInt(line);

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

    //--------------------------------------MANAGERS TABLE--------------------------------------------
    //---------------------------------CREATE MANAGERS ------------------------------  
    private static void createManager(Scanner keyboard, Model model) {
        Manager m = readManager(keyboard);
        if (model.addManager(m)) {
            System.out.println("Manager succesfully added to the database!\n");
        } else {
            System.out.println("Manager Not added to the database!\n");
        }
    }

    //---------------------------------DELETE MANAGERS ------------------------------  
    private static void deleteManager(Scanner keyboard, Model model) {
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
    }

    //------------------------------edit an event---------------------------
    private static void editManager(Scanner kb, Model model) {
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

    }

    //---------------------------------VIEW MANAGERS ------------------------------  
    private static void viewManagers(Model mdl) {
        List<Manager> managers = mdl.getManagers();
        System.out.println();
        if (managers.isEmpty()) {
            System.out.println("There are no Managers to see");
        } else {
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

    private static void doEventMenu(Scanner keyboard, Model model) {
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
            System.out.println("-5 Back To Tables.");

            System.out.print("-Enter Option:");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

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
            }
        } //Once Not Equals To 5 Programes Runs Else Stops:
        while (opt != 5);
    }

    private static void doManagerMenu(Scanner keyboard, Model model) {
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
            System.out.println("-5 Back To Tables.");

            System.out.print("-Enter Option:");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);

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
                    viewManagers(model);
                    break;
                }
            }
        } //Once Not Equals To 5 Programes Runs Else Stops:
        while (opt != 5);
    }

}
