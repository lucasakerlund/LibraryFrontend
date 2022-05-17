package com.stav.libraryfrontend.controllers.models.groupRooms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class GroupRooms extends BorderPane {

    private static GroupRooms instance = new GroupRooms();

    @FXML
    private Label showAllRooms;

    @FXML
    private ComboBox libraryComboBox;

    @FXML
    private FlowPane contentFlowPane;

    private GroupRooms(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/groupRooms/groupRooms.fxml"));
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
        showAllRooms.setOnMousePressed(e -> {
            contentFlowPane.getChildren().add(new RoomBox());
        });
    }

    public static GroupRooms inst(){
        return instance;
    }

}
