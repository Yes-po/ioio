package org.example;

public class PdfCreator extends ProductCreator{
    @Override
    public Product createProduct() {
        return new Pdf();
    }
}
