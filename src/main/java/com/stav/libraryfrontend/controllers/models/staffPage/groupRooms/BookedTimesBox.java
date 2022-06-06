package com.stav.libraryfrontend.controllers.models.staffPage.groupRooms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BookedTimesBox extends BorderPane {

    @FXML
    private Label accountLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label timeLabel;

    public BookedTimesBox(String accountName, String time, String date){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/groupRooms/bookedTimesBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        accountLabel.setText(accountName);
        timeLabel.setText(time);
        dateLabel.setText(date);

        setup();
    }

    public void setup(){

    }
}
