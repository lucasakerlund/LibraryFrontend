package com.stav.libraryfrontend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoanedBook {

    private int bookId;
    private int customerId;
    private String loanDate;
    private String returnDate;

    public LoanedBook(int bookId, int customerId, String loanDate, String returnDate) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
