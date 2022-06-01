package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class GroupRoomBox extends Label {

    public GroupRoomBox(String roomName, String libraryName, String bookedTime, String customerName){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/groupRoomBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setText(roomName);
        setup(roomName, libraryName, bookedTime, customerName);
    }

    public void setup(String roomName, String libraryName, String bookedTime, String customerName){
        this.setOnMousePressed(e -> {
            SubSceneHandler.inst().show(new ReservedGroupRoomsView(roomName, libraryName, bookedTime, customerName));
        });
    }
}
