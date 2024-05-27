package org.example;

import java.util.Deque;
import java.util.Scanner;

public class Admin extends Person {

    public Admin(String login, String password, int id){
        init("ADMIN", login, password, id, true);
    }

    public Admin(){
        this.personRole = "ADMIN";
    }

    public Admin(Deque<String>csv_args){
        super(csv_args);
    }

    public void addEvent(Sys system, Scanner scanner){
        boolean success = false;
        System.out.println("Podaj nazwe:");
        String name = scanner.next();
        System.out.println("Podaj genre:");
        String genre = scanner.next();

        int id;
        System.out.println("Podaj id eventu");
        id = scanner.nextInt();

        while(system.getCatalog().getEventById(id) != null){
            System.out.println("Podane id jest już zajęte, spróbuj jeszcze raz");
            id = scanner.nextInt();
        }

        Event event = new Event(name, genre, id);
        system.getCatalog().addEvent(event);
        System.out.println("Dodalem event");
    }

    public void addTickets(Sys system, Scanner scanner){
        boolean success = false;
        int id;
        System.out.println("Podaj id eventu");
        id = scanner.nextInt();

        if(system.getCatalog().getEventById(id) == null){
            System.out.println("Nie istnieje event z podanym id");
            return;
        }
        Event event = system.getCatalog().getEventById(id);

        System.out.println("Podaj typ biletu");
        String type = scanner.next();
        System.out.println("Podaj cene biletu");
        float price = scanner.nextFloat();
        System.out.println("Podaj liczbę biletow");
        int amount = scanner.nextInt();

        for(int i = 0; i< amount; ++i){
            Ticket ticket = new Ticket();
            ticket.price = price;
            ticket.eventId = event.id;
            ticket.type = type;
            event.tickets.add(ticket);
        }


        System.out.println("Dodalem event(y)");
    }
}
