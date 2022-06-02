package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class GroupRoomBookings extends ScrollPane {

    @FXML
    private FlowPane content;

    private static GroupRoomBookings instance = new GroupRoomBookings();

    private GroupRoomBookings(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/groupRoomBookings.fxml"));
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

    public void addRoomBox(GroupRoomBox grb){
        content.getChildren().add(grb);
    }

    public static GroupRoomBookings inst(){
        return instance;
    }
}
