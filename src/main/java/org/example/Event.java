package org.example;

import java.util.ArrayList;
import java.util.List;

public class Event {
    public String name;
    public String genre;
    public int id;
    public List<Ticket> tickets;

    //public List<Float> prices; Nie potrzebne, cena jest pamiętana przez same bilety

    public Event(String name, String genre, int id){
        this.name = name;
        this.genre = genre;
        this.id = id;
        tickets = new ArrayList<>();
    }

    public boolean addToOrder(List<Ticket> tickets){
        if (this.tickets.isEmpty())
            return false;
        Ticket ticket = this.tickets.remove(0);
        tickets.add(ticket);
        return true;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", id " + id + "}";
    }
}
