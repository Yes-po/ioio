package org.example;

public class Pdf implements Product {
    @Override
    public String deliver() {
        return "PDF TICKETS";
    }
}
