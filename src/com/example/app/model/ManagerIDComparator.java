package com.example.app.model;

//package com.example.app.model;


import java.util.Comparator;

public class ManagerIDComparator implements Comparator<Manager>{

    @Override
    public int compare(Manager m1, Manager m2) {
        return (int) (m1.getManagerID() - m2.getManagerID());
        //return getManagerID;
        
    }
    
}
