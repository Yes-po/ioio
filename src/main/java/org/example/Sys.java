package org.example;

public class Sys implements CustomerSys, AdminSys {
    private Catalog catalog;
    private Auth auth;

    public Sys(){
        catalog = new Catalog();
    }


    @Override
    public boolean addEvent() {
        return false;
    }

    @Override
    public void buyTickets(Order o) {

    }

    public void browseCatalog(){
        catalog.browse();
    }

    public boolean authenticate(String login,String password){
        this.auth = Auth.getInstance();
        return auth.authenticate(login,password) != null;
    }

    public boolean register(Person person){
       return Auth.getInstance().addPerson(person);
    }

    public Product getProduct(Order o){
        ProductCreator pc;
        if(o.isStream){
            pc = new StreamCreator();
        }
        else{
            pc = new PdfCreator();
        }
        return pc.createProduct();
    }
}
