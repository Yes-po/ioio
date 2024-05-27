package org.example;

import java.util.Scanner;

public class Menu {
    private Sys system;
    private Scanner scanner = new Scanner(System.in);

    public Menu(Sys system) {
        this.system = system;
    }


    int getNrFromUser(){
        int userChoice = 0;

        userChoice = scanner.nextInt();
        return  userChoice;
    }
    void mainMenu(){
        System.out.println("Co chcesz zrobic?");
        System.out.println("1. zalogować się");
        System.out.println("2. Załozyc konto");
        System.out.println("3. Przegladac eventy");
        System.out.println("3. Wybierz  event i kup bilet");

        int userChoice = getNrFromUser();

        switch (userChoice){
            case 1 ->  loginMenu();
            case 2 -> registerMenu();
            case 3 -> system.browseCatalog();
            case 4 -> buy();
        }

    }

    private void buy(){
        System.out.println("Podaj numer eventu: ");
        int eventNum = scanner.nextInt();
        System.out.println("Ile biletow: ");
        int ticketNum = scanner.nextInt();
        Order o = new Order();

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

        if (result)
            System.out.println("Dostep udzielony");
        else
            System.out.println("Zle dane");
    }
}
