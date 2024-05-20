package org.example;

public class Sys implements CustomerSys, AdminSys {
    private Catalog catalog;
    private Auth auth;


    @Override
    public boolean addEvent() {
        return false;
    }

    @Override
    public void buyTickets(Order o) {

    }

    public void browseCatalog(){

    }

    public boolean authenticate(String login,String password){
        this.auth = Auth.getInstance();
        return auth.authenticate(login,password) != null;
    }

    public boolean register(Person person){
       return Auth.getInstance().addPerson(person);
    }
    public Product getProduct(Order o){
        return null;
    }
}
