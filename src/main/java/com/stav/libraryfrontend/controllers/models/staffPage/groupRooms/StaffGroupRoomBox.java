package com.stav.libraryfrontend.controllers.models.staffPage.groupRooms;

import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class StaffGroupRoomBox extends StackPane {

    @FXML
    private Label roomNameLabel;

    public int id;

    public String roomDescription;

    public String libName;


    public StaffGroupRoomBox(int room_id, String name, String description, String libraryName){
        id = room_id;
        roomDescription = description;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/groupRooms/staffGroupRoomBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        roomNameLabel.setText(name);
        libName = libraryName;

        setup();
    }

    public void setup(){
        this.setOnMousePressed(e -> {
            SubSceneHandler.inst().show(new StaffGroupRoomsView(id, roomNameLabel.getText(), libName));
        });
    }

}