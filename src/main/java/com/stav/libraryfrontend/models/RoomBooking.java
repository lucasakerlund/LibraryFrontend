package com.stav.libraryfrontend.models;

import javafx.scene.layout.BorderPane;

public class RoomBooking extends BorderPane {
    private int time_id;
    private int customer_id;

    public RoomBooking(int time_id, int customer_id){
        this.time_id = time_id;
        this.customer_id = customer_id;
    }

    public void setTime_id(int time_id) {
        this.time_id = time_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }
}
