package com.stav.libraryfrontend.controllers.models.groupRooms;

import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class RoomBox extends StackPane {

    @FXML
    private Label roomNameLabel;

    public RoomBox(){

        String[] placeHolder = placeholderBox().split(",");


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/groupRooms/roomBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        roomNameLabel.setText(placeHolder[0]);

        String avalibleSeats = placeHolder[1]; // If this needs to be used...

        setup();
    }

    public void setup(){
        this.setOnMousePressed(e -> {
            SubSceneHandler.inst().show(new RoomView());
        });
    }

    public String placeholderBox(){
        String nameSeats = "bigRoom,10";
        return nameSeats;
    }

}
