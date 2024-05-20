package org.example;

import java.util.Deque;

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

    public boolean addEvent(){
        System.out.println("Dodalem event");
        return true;
    }
}
