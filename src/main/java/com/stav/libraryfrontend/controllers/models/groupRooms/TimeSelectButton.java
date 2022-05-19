package com.stav.libraryfrontend.controllers.models.groupRooms;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class TimeSelectButton extends Label{

    @FXML
    private Label timeSelectButton;

    private String time;

    public TimeSelectButton(String time){
        this.time = time;
        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("/com/stav/libraryfrontend/fxml/groupRooms/timeSelectButton.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        timeSelectButton.setText(time);
    }

    public String getTime() {
        return time;
    }

}
