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
    public void buyTickets(Order o, Customer customer) {
        customer.tickets.addAll(o.tickets);
        System.out.println("Dodano bilety do konta");
        System.out.println("Milego ogladania");
    }

    public void browseCatalog(){
        catalog.browse();
    }

    public Catalog getCatalog(){return catalog;}
    public Person authenticate(String login,String password){
        this.auth = Auth.getInstance();
        return auth.authenticate(login,password);
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
