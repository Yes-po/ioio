package org.example;

public class Main {
    public static void main(String[] args) {
        Sys system = new Sys();
        Menu menu = new Menu(system);
        menu.mainMenu();
    }
}