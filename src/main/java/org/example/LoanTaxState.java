package org.example;

public enum LoanTaxState {
    PAYD("Pago"),
    OWING("Em Dívida");

    private String string;

    LoanTaxState(String string) {
        this.string = string;
    }
    public String toString() {
        return string;
    }
}

