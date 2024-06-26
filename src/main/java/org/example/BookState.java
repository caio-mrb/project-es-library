package org.example;

public enum BookState {
    BORROWED("Emprestado"),
    RESERVED("Reservado"),
    RETURNED("Devolvido");

    private final String string;

    BookState(String string) {
        this.string = string;
    }

    public String toString() {
        return string;
    }
}
