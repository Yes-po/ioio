package org.example;

import java.util.Scanner;

public class Menu {
    private Sys system;
    private Scanner scanner = new Scanner(System.in);

    private Person user;

    public Menu(Sys system) {
        this.system = system;
    }


    int getNrFromUser(){
        int userChoice = 0;

        userChoice = scanner.nextInt();
        return  userChoice;
    }
    void mainMenu(){
        while (true){
            System.out.println("Co chcesz zrobic?");
            System.out.println("1. zalogować się");
            System.out.println("2. Załozyc konto");
            System.out.println("3. Przegladac eventy");
            System.out.println("4. Wybierz  event i kup bilet");
            System.out.println("5. Przejrzeć swoje bilety");
            System.out.println("6. Dodaj nowy event (ADMIN)");
            System.out.println("7. Dodaj bilety do istniejącego eventu (ADMIN)");
            System.out.println("0. Wylogować się");
            int userChoice = getNrFromUser();

            switch (userChoice) {
                case 0 -> logout();
                case 1 -> loginMenu();
                case 2 -> registerMenu();
                case 3 -> system.browseCatalog();
                case 4 -> buy();
                case 5 -> browse_tickets();
                case 6 -> add_event();
                case 7 -> add_tickets();
            }
        }
    }

    private void browse_tickets() {
        if (!(user instanceof Customer))
        {
            System.out.println("Musisz być zalogowany jako klient, aby przegladac swoje bilety");
            return;
        }
        Customer c = (Customer) this.user;
        c.browseTickets();
    }

    private void logout() {
    this.user = null;
    }

    private void add_tickets() {
        if (!(user instanceof Admin))
        {
            System.out.println("Musisz być zalogowany jako Admin, aby dodawać bilety");
            return;
        }
        Admin admin = (Admin) this.user;
        admin.addTickets(system, scanner);
    }

    private void add_event() {
        if (!(user instanceof Admin))
        {
            System.out.println("Musisz być zalogowany jako Admin, aby dodawać eventy");
            return;
        }
        Admin admin = (Admin) this.user;
        admin.addEvent(system, scanner);
    }

    private void buy(){
        if (!(user instanceof Customer))
        {
            System.out.println("Musisz być zalogowany jako klient, aby kupować bilety");
            return;
        }
        Customer c = (Customer) this.user;
        System.out.println("Podaj id eventu: ");
        int eventID = scanner.nextInt();
        System.out.println("Ile biletow: ");
        int ticketNum = scanner.nextInt();
        System.out.println("Live/Stream [0/1]");
        int isStream = scanner.nextInt();
        Order o = new Order();
        if(isStream==1)
            o.setStream();

        Event event = system.getCatalog().getEventById(eventID);

        if (event == null){
            System.out.println("Podane event nie istnieje");
            return;
        }

        int success_count = 0;

        for (int i = 0; i< ticketNum; ++i)
            if(event.addToOrder(o.tickets))
                success_count++;

        if (success_count != ticketNum){
            System.out.println("Nie udało się zdobyć żądanej liczby biletów");
            event.tickets.addAll(o.tickets); // Return tickets back to ticket poll
            return;
        }

        Float price = o.tickets.stream().map( ticket -> ticket.price).reduce(Float::sum).orElse(0.f);
        System.out.println(String.format("Ostateczny koszt tej operacji to %f złotych, kontynuować? [Y/N]", price));

        String choice = scanner.next();

        if (choice.equalsIgnoreCase( "Y" )){
            System.out.println("Dokonuje zakupu");
            system.buyTickets(o, c);
        } else
            System.out.println("Zakup anulowano");

        event.tickets.addAll(o.tickets); // Return tickets back to ticket poll
    }
    private void registerMenu() {
        System.out.println("Podaj swoj login");
        String login = scanner.next();

        System.out.println("Podaj swoje haslo");
        String password = scanner.next();
        System.out.println("Potwierdź hasło");
        String password2 = scanner.next();

        // id zostannie zmienione przy zapisie.
        Customer person = new Customer(login, password, 0);

        if (password.equals(password2)){
            boolean success = system.register(person);

            if (success)
                System.out.println("Pomylsnie zarejestrowano użytkownika");
            else
                System.out.println("Jesteś już bazie");
        }
        else{
            System.out.println("Podane hasła są różne");
        }
    }

    void loginMenu(){
        System.out.println("Podaj swoj login:");
        String login = scanner.next();
        System.out.println("Podaj swoje haslo");
        String password = scanner.next();

        var result = system.authenticate(login, password);

        if (result != null) {
            System.out.println("Dostep udzielony");
            this.user = result;
        }
        else
            System.out.println("Zle dane");
    }
}
