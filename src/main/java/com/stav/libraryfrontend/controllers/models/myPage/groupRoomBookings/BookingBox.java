package com.stav.libraryfrontend.controllers.models.myPage.groupRoomBookings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BookingBox extends BorderPane {
    @FXML
    private Label roomNameLabel;

    @FXML
    private Label bookingDateLabel;

    @FXML
    private Label bookingTimeLabel;

    @FXML
    private Label cancelButton;



    public BookingBox(String roomName, String bookingDate, String bookingTime){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/groupRoomBookings/bookingBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        roomNameLabel.setText(roomName);
        bookingDateLabel.setText(bookingDate);
        bookingTimeLabel.setText(bookingTime);

        setup();
    }

    public void setup(){
        cancelButton.setOnMousePressed(e -> {
            System.out.println("AVBOKNING WOOP WOOP");
        });
    }
}
