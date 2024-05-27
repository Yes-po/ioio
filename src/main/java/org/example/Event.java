package org.example;

import java.util.List;

public class Event {
    public String name;
    public String genre;
    public List<Ticket> tickets;
    public List<Float> prices;

    public Event(String name, String genre){
        this.name = name;
        this.genre = genre;
    }

    public boolean addToOrder(List<Ticket> tickets){
        return false;
    }


    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' + "}";
    }
}
