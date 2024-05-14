package org.example;

import java.util.ArrayList;
import java.util.List;

public class Auth {
    private List<Person> people = new ArrayList<Person>();
    private static Auth instance;
    public Person authenticate(String login, String password){
        boolean isIn = false;
        for(var p: people) {

            if(p.login.equals(login) && p.password.equals(password)){
                return p;
            }
            if(p.login.equals(login)){
                isIn=true;
                System.out.println("Zle haslo");
                return null;
            }
        }
        System.out.println("Nie ma cie w bazie.");
        return null;

    }
    public boolean loginTaken(String login){
        for (Person p : people){
            if (p.login.equals(login))
                return true;
        }
        return false;
    }
    public void addPerson(Person p){
        this.people.add(p);
    }

    public static Auth getInstance(){
        if(instance == null){
            instance = new Auth();
        }
        return instance;

    }
}
