package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ReservedGroupRoomsView extends BorderPane {

    @FXML
    private Label bookedTimeLabel;

    @FXML
    private Label cancelBookingButton;

    @FXML
    private Label customerNameLabel;

    @FXML
    private Label libraryNameLabel;

    @FXML
    private Label roomNameLabel;

    public ReservedGroupRoomsView(String roomName, String libraryName, String bookedTime, String customerName){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/reservedGroupRoomsView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public void setup(){

    }
}
