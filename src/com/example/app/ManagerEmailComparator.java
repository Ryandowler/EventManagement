package com.example.app;
import com.example.app.model.Manager;
import java.util.Comparator;

public class ManagerEmailComparator implements Comparator<Manager>{

    @Override
    public int compare(Manager m1, Manager m2) {
        return (int) (m1.getManagerID() - m2.getManagerID());
    }
    
}

