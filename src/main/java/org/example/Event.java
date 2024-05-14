package org.example;

import java.util.List;

public class Event {
    public List<Ticket> tickets;
    public List<Float> prices;

    public boolean addToOrder(List<Ticket> tickets){
        return false;
    }
}
