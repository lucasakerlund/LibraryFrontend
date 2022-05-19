package com.stav.libraryfrontend.models;

public class BookQueue {

    private int bookId;
    private int customerId;
    private String reservationDate;

    public BookQueue(int bookId, int customerId, String reservationDate) {
        this.bookId = bookId;
        this.customerId = customerId;
        this.reservationDate = reservationDate;
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

    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
}
