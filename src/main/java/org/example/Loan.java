package org.example;

import java.time.LocalDate;
public class Loan {

    private final int code;
    private Book book;
    private QuantityBook quantityBook;
    private int deadline;
    private BookState state;
    private LocalDate requestDate;
    private LocalDate returnDate;
    private int loanTaxValue;

    public Loan(int code, Book book, QuantityBook quantityBook, int deadline) {
        this.code = code;
        this.book = book;
        this.quantityBook = quantityBook;
        this.deadline = deadline;
        this.state = BookState.RESERVED;
    }

    public Loan(int code, Book book, QuantityBook quantityBook, int deadline, LocalDate requestDate) {
        this(code, book, quantityBook, deadline);
        this.state = BookState.BORROWED;
        this.requestDate = requestDate;
    }

    public Loan(int code, Book book, QuantityBook quantityBook, int deadline, LocalDate requestDate, LocalDate returnDate, int loanTaxValue) {
        this(code, book, quantityBook, deadline, requestDate);
        this.state = BookState.RETURNED;
        this.returnDate = returnDate;
        this.loanTaxValue = loanTaxValue;
    }

    public int getCode() {
        return code;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public BookState getState() {
        return state;
    }

    public void setState(BookState state) {
        this.state = state;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getLoanTaxValue() {
        return loanTaxValue;
    }

    public void setLoanTaxValue(int loanTax) {
        this.loanTaxValue = loanTax;
    }
}
