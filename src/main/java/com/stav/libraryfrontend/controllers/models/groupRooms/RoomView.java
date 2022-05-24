package com.stav.libraryfrontend.controllers.models.groupRooms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class RoomView extends BorderPane {

    @FXML
    private Label roomName;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Label bookRoomButton;

    @FXML
    private VBox timesVbox;

    @FXML
    private Label messageLabel;

    private TimeSelectButton focused;

    public RoomView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/groupRooms/roomView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Hides message label by default until it is needed
        messageLabel.setText("");
        setup();
    }

    public void setup() {
        placeholder(); // Remove this when we have actual rooms from Database

        // Loop through and add all available times for booking rooms
        addTime("05:00-07:00");
        addTime("07:00-09:00");
        addTime("09:00-11:00");
        addTime("11:00-13:00");
        addTime("13:00-15:00");

        // This button must remove the available time from the room and save it for the current user
        bookRoomButton.setOnMousePressed(e->{
            // If nothing is selected, tell user
            if(focused == null){
                messageLabel.setId("room-view-message-label");
                messageLabel.setText("Ett rum måste markeras först!");
            } else {
                System.out.println(focused.getTime());
                messageLabel.setId("room-view-message-label-green");
                messageLabel.setText("Du har bokat tiden: " + focused.getTime());
                timesVbox.getChildren().remove(focused);
                // Removes highlight
                focused = null;
            }
        });

    }

    private void addTime(String time){
        TimeSelectButton selectButton = new TimeSelectButton(time);
        selectButton.setOnMousePressed(e->{
            if (focused != null){
                focused.setId("");
            }
            focused = selectButton;
            focused.setId("time-select-button-focused");
        });

        timesVbox.getChildren().add(selectButton);
    }


    // This placeholder adds in the temporary values before we get information from database
    public void placeholder() {
        roomName.setText("Big old room");
        descriptionArea.setText("This is a big old room, huge enough to fit me and my ego! :)");
    }


}

