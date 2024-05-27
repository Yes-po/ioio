package org.example;

import java.util.Deque;

public class Customer extends Person{
    Customer(String login, String password, int id){
        init("CUSTOMER", login, password, id, false);
    }

    Customer(){
        this.personRole = "CUSTOMER";
    }

    public Customer(Deque<String> csv_args){
        super(csv_args);
    }
}
