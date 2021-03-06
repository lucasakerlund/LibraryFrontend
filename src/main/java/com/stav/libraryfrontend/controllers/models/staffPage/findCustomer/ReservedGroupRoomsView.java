package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import com.stav.libraryfrontend.models.Customer;
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

    private int timeId;
    private Customer customer;

    public ReservedGroupRoomsView(String roomName, String libraryName, String bookedTime, int timeId, Customer customer){
        this.timeId = timeId;
        this.customer = customer;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/reservedGroupRoomsView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        roomNameLabel.setText(roomName);
        libraryNameLabel.setText(libraryName);
        bookedTimeLabel.setText(bookedTime);
        customerNameLabel.setText(customer.getFirstName() + " " + customer.getLastName());

        setup();
    }

    public void setup(){
        cancelBookingButton.setOnMousePressed(e -> {
            BackendCaller.inst().removeGroupRoomBooking(timeId, customer.getCustomerId());
            FindCustomerPage.inst().getGroupRoomBookingsInfo(customer);
            SubSceneHandler.inst().hide();
        });
    }
}
