package org.example;

public class LoanTax {
    private Loan loan;
    private LoanTaxState loanTaxState;

    public LoanTax(Loan loan, LoanTaxState loanTaxState) {
        this.loan = loan;
        this.loanTaxState = loanTaxState;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public LoanTaxState getLoanTaxState() {
        return loanTaxState;
    }

    public void setLoanTaxState(LoanTaxState loanTaxState) {
        this.loanTaxState = loanTaxState;
    }
}
