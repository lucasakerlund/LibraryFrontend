package com.stav.libraryfrontend.controllers.models.staffPage.groupRooms;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.models.Customer;
import com.stav.libraryfrontend.models.GroupRoomTime;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.List;

public class StaffGroupRoomsView extends BorderPane {

    @FXML
    private VBox allBookedTimesVbox;

    @FXML
    private VBox allTimesVbox;

    @FXML
    private Label libraryNameLabel;

    @FXML
    private Label roomNameLabel;

    @FXML
    private Label roomIdLabel;

    public StaffGroupRoomsView(int room_id, String name, String libraryName){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/groupRooms/staffGroupRoomsView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        roomIdLabel.setText("Room ID: " + room_id);
        roomNameLabel.setText(name);
        libraryNameLabel.setText(libraryName);

        addTimes(room_id);
        setup();
    }

    public void addTimes(int room_id){
        // TESTING
        BackendCaller.inst().getAllGroupRoomBookings();


        List<GroupRoomTime> availableRoomTimes = BackendCaller.inst().getGroupRoomTimes(room_id);
        for (int i = 0; i < availableRoomTimes.size(); i++) {
            allTimesVbox.getChildren().add(new AvailableTimesBox(availableRoomTimes.get(i).getTime(), availableRoomTimes.get(i).getDate()));
        }
    }

    public void setup(){

    }
}
