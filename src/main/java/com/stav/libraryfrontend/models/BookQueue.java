package com.stav.libraryfrontend.models;

public class BookQueue {

    private String isbn;
    private int customerId;
    private String reservationDate;

    public BookQueue(String isbn, int customerId, String reservationDate) {
        this.isbn = isbn;
        this.customerId = customerId;
        this.reservationDate = reservationDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
