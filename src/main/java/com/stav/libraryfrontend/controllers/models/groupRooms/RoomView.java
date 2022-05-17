package com.stav.libraryfrontend.controllers.models.groupRooms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class RoomView extends BorderPane {

    @FXML
    private Label roomName;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Label bookRoomButton;

    @FXML
    private TextArea avalibilityTextArea; // This label tells user if room is avalible or not, use it to say when room will be avalible (date)

    public RoomView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/groupRooms/roomView.fxml"));
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
        placeholder();

        bookRoomButton.setOnMousePressed(e -> {
            //Code here to make a booking yes?
            if (avalibilityTextArea.getText().equals("Rummet finns tillgängligt för bokning!")){
                System.out.println("DU HAR BOKAT ETT RUM! Bra jobbat.");
                // Set room is booked = true  <- HÄR

            } else {
                System.out.println("Rummet är bokat, bruh!!");
            }

        });
    }

    public void placeholder(){
        roomName.setText("Big old room");
        descriptionArea.setText("This is a big old room, huge enough to fit me and my ego! :)");
        avalibilityTextArea.setText("Rummet finns tillgängligt för bokning!");
    }

}
