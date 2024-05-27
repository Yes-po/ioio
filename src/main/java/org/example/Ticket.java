package org.example;

public class Ticket {
    // public int ticketId; // póki co nie używane
    public int eventId;
    public String type;
    public float price;
    public int customerId;

    public boolean changeOwner(Customer c){
      return false;
    }
}
