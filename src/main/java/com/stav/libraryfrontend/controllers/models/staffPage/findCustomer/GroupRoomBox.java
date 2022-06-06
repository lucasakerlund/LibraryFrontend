package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import com.stav.libraryfrontend.models.Customer;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class GroupRoomBox extends Label {

    private String roomName;
    private String libraryName;
    private String bookedTime;
    private int timeId;
    private Customer customer;

    public GroupRoomBox(String roomName, String libraryName, String bookedTime, int timeId, Customer customer){
        this.roomName = roomName;
        this.libraryName = libraryName;
        this.bookedTime = bookedTime;
        this.timeId = timeId;
        this.customer = customer;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/groupRoomBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setText(roomName);
        setup();
    }

    public void setup(){
        this.setOnMousePressed(e -> {
            SubSceneHandler.inst().show(new ReservedGroupRoomsView(roomName, libraryName, bookedTime, timeId, customer));
        });
    }
}
