package org.example;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    public List<Event> events;

    public Catalog(){
        events = new ArrayList<>();
        events.add(new Event("Motor-Gornik","Pilka Nozna"));
        events.add(new Event("Taylor Swift","Koncert"));
        events.add(new Event("Kielce-Wisla","Pilka Reczna"));
    }
    public void browse(){
        for(var event: this.events){
            System.out.println(event);
        }
    }

    public void addEvent(){

    }
}
