package org.example;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    public List<Event> events;

    public Catalog(){
        events = new ArrayList<>();
        events.add(new Event("Motor-Gornik","Pilka Nozna", 1) );
        events.add(new Event("Taylor Swift","Koncert", 2));
        events.add(new Event("Kielce-Wisla","Pilka Reczna", 3) );
    }
    public void browse(){
        for(var event: this.events){
            System.out.println(event);
        }
    }

    public Event getEventById(int id){
        for (Event event: events)
            if (event.id == id)
                return event;
        return null;
    }

    public void addEvent(Event event){
        events.add(event);
    }
}
