package com.example.app.model;

//package com.example.app.model;

import com.example.app.model.Event;
import java.util.Comparator;

public class EventCostComparator implements Comparator<Event>{

    @Override
    public int compare(Event e1, Event e2) {
        return (int) (e1.getCost() - e2.getCost());
        //return getManagerID;
        
    }
    
}