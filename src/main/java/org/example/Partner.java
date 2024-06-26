package org.example;

import java.time.LocalDate;
import java.util.LinkedList;

public class Partner extends Searchable{

    private int nif;
    private Boolean isActive;
    private LocalDate birthdayDate;
    private int phone;
    private String email;
    private String address1;
    private String address2;
    private String postalCode;
    private int maxAmountLoan;
    private int maxDeadlineLoan;
    private boolean isBlocked;
    private LinkedList<Loan> loans;
    private LinkedList<LoanTax> loanTaxes;

    public Partner(int code, String name, int nif, LocalDate birthdayDate, int phone, String email, String address1, String postalCode) {
        super(code, name);
        this.nif = nif;
        this.isActive = true;
        this.birthdayDate = birthdayDate;
        this.phone = phone;
        this.email = email;
        this.address1 = address1;
        this.postalCode = postalCode;
        this.loans = new LinkedList<>();
        this.loanTaxes = new LinkedList<>();
        this.isBlocked = false;
        this.maxAmountLoan = 0;
        this.maxDeadlineLoan = 0;
    }

    public Partner(int code, String name, int nif, LocalDate birthdayDate, int phone, String email, String address1, String postalCode, String address2) {
        this(code, name, nif, birthdayDate, phone, email, address1, postalCode);
        this.address2 = address2;
    }

    public Partner(int code, String name, int nif, LocalDate birthdayDate, int phone, String email, String address1, String postalCode, String address2, int maxAmountLoan, int maxDeadlineLoan) {
        this(code, name, nif, birthdayDate, phone, email, address1, postalCode, address2);
        this.maxAmountLoan = maxAmountLoan;
        this.maxDeadlineLoan = maxDeadlineLoan;
    }

    public void addLoan(Loan loan) {
        this.loans.add(loan);
    }

    public LinkedList<LoanTax> getLoanTaxes() {
        return new LinkedList<>(loanTaxes);
    }

    public void setLoanTaxes(LinkedList<LoanTax> loanTaxes) {
        this.loanTaxes = loanTaxes;
    }

    public LinkedList<Loan> getLoans() {
        return new LinkedList<>(loans);
    }

    public void setLoans(LinkedList<Loan> loans) {
        this.loans = loans;
    }

    public int getMaxAmountLoan() {
        return maxAmountLoan;
    }

    public void setMaxAmountLoan(int maxAmountLoan) {
        this.maxAmountLoan = maxAmountLoan;
    }

    public int getMaxDeadlineLoan() {
        return maxDeadlineLoan;
    }

    public void setMaxDeadlineLoan(int maxDeadlineLoan) {
        this.maxDeadlineLoan = maxDeadlineLoan;
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

    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(LocalDate birthdayDate) {
        this.birthdayDate = birthdayDate;
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
}
