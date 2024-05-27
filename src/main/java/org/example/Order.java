package org.example;

import java.util.ArrayList;
import java.util.List;

public class Order {

    public int id;
    public int customerId;
    public float total;
    public List<Ticket> tickets = new ArrayList<>();
    public boolean isStream;

    public void addToOrder(int customerId, List<Ticket> tickets){
        this.customerId = customerId;
        this.tickets.addAll(tickets);
    }

    public void setStream(){
        this.isStream = true;
    }
}
