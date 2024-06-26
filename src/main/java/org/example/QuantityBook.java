package org.example;

public class QuantityBook {

    private final int code;
    private String isbn;
    private String bookcase;
    private String bookshelf;
    private BookState bookState;

    public QuantityBook(int code, String isbn, String bookcase, String bookshelf) {
        this.code = code;
        this.isbn = isbn;
        this.bookcase = bookcase;
        this.bookshelf = bookshelf;
        this.bookState = BookState.RETURNED;
    }

    public BookState getBookState() {
        return bookState;
    }

    public void setBookState(BookState bookState) {
        this.bookState = bookState;
    }

    public int getCode() {
        return code;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookcase() {
        return bookcase;
    }

    public void setBookcase(String bookcase) {
        this.bookcase = bookcase;
    }

    public String getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(String bookshelf) {
        this.bookshelf = bookshelf;
    }
}
