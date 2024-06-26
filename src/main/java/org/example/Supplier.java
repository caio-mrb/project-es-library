package org.example;

import java.util.LinkedList;

public class Supplier extends Searchable {

    private int nif;
    private Boolean isActive;
    private int phone;
    private String email;
    private String address1;
    private String address2;
    private String postalCode;
    private LinkedList<Book> books;

    public Supplier(int code, String name, int nif, int phone, String email, String address1, String postalCode) {
        super(code, name);
        this.nif = nif;
        this.isActive = true;
        this.phone = phone;
        this.email = email;
        this.address1 = address1;
        this.postalCode = postalCode;
        this.books = new LinkedList<>();
    }

    public Supplier(int code, String name, int nif, int phone, String email, String address1, String postalCode, String address2) {
        this(code, name, nif, phone, email, address1, postalCode);
        this.address2 = address2;
    }

    public void addBook(Book book) {
        books.add(book);
    }


    public void removeBook(Book book) {
        books.remove(book);
    }

    public LinkedList<Book> getBooks() {
        return new LinkedList<>(books);
    }

    public void setBooks(LinkedList<Book> books) {
        this.books = books;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String toString() {
        return this.getName();
    }
}
