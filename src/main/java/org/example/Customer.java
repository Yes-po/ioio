package org.example;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Customer extends Person{
    Customer(String login, String password, int id){
        init("CUSTOMER", login, password, id, false);
    }

    Customer(){
        this.personRole = "CUSTOMER";
    }

    public List<Ticket> tickets = new ArrayList<>();

    public Customer(Deque<String> csv_args){
        super(csv_args);
    }

    public void browseTickets(){
        System.out.printf(String.format("Bilety użytkownika %s: ", login));

        for (Ticket t: tickets){
            String ticket_string = String.format("Bilet %s o id, na event od id %d. Cena %f", t.type, t.eventId, t.price);
            System.out.println(ticket_string);
        }
    }
}
