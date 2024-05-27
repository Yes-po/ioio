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

        int userChoice = getNrFromUser();

        switch (userChoice){
            case 1 ->  loginMenu();
            case 2 -> registerMenu();
        }

    }

    private void registerMenu() {
        System.out.println("Podaj swoj login");
        String login = scanner.next();
        if()

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
            System.out.println("Wynocha brudny hakerze");
    }
}
