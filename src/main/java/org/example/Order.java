package org.example;

import java.util.List;

public class Order {

    public int id;
    public int customerId;
    public float total;
    public List<Ticket> tickets;
    public boolean isStream;

    public void addToOrder(int customerId, List<Ticket> tickets){


    }

    public void setStream(){
        this.isStream = true;
    }
}
