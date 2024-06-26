package org.example;

import java.util.LinkedList;

public class Book extends Searchable{

    private String author;
    private int edition;
    private String genre;
    private String subgenre;
    private Supplier supplier;
    private Boolean isActive;
    private LinkedList<QuantityBook> quantityBooks;
    private int requestsCounter;

    public Book(int code, String name, String author, int edition, String genre, String subgenre, Supplier supplier) {
        super(code,name);
        this.author = author;
        this.edition = edition;
        this.genre = genre;
        this.subgenre = subgenre;
        this.supplier = supplier;
        this.isActive = true;
        this.quantityBooks = new LinkedList<>();
        this.requestsCounter = 0;
    }

    public void addRequestCounter() {
        this.requestsCounter++;
    }

    public int getRequestsCounter() {
        return requestsCounter;
    }


    public void addQuantityBook(QuantityBook quantityBook){
        quantityBooks.add(quantityBook);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LinkedList<QuantityBook> getQuantityBooks() {
        return quantityBooks;
    }

    public void setQuantityBooks(LinkedList<QuantityBook> quantityBooks) {
        this.quantityBooks = quantityBooks;
    }

    public String toString(){
        return getName() + " - " + getAuthor();
    }

}
