package com.stav.libraryfrontend.controllers.models.groupRooms;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.UserDetails;
import com.stav.libraryfrontend.controllers.models.myPage.groupRoomBookings.MyBookingsPage;
import com.stav.libraryfrontend.models.GroupRoomTime;
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

    private int roomId;

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Label bookRoomButton;

    @FXML
    private VBox timesVbox;

    @FXML
    private Label messageLabel;

    @FXML
    private Label libraryNameLabel;

    private TimeSelectButton focused;

    public RoomView(int id, String name, String description, String libraryName) {
        this.roomId = id;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/groupRooms/roomView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        roomName.setText(name);
        descriptionArea.setText(description);
        libraryNameLabel.setText(libraryName);

        // Hides message label by default until it is needed
        messageLabel.setText("");
        setup();
    }

    public void setup() {
        loadTimes();

        bookRoomButton.setOnMousePressed(e->{
            // If nothing is selected, tell user
            if(focused == null){
                messageLabel.setId("room-view-message-label");
                messageLabel.setText("Ett rum måste markeras först!");
            } else {
                if(!BackendCaller.inst().bookGroupRoom(focused.getGroupRoomTime().getTime_id(), UserDetails.inst().getCustomer().getCustomerId())) {
                    messageLabel.setId("room-view-message-label");
                    messageLabel.setText("Något fel inträffade!");
                    return;
                }
                messageLabel.setId("room-view-message-label-green");
                messageLabel.setText("Du har bokat tiden: " + focused.getGroupRoomTime().getTime());
                timesVbox.getChildren().remove(focused);
                // Removes highlight
                focused = null;
                MyBookingsPage.inst().loadBookings();
            }
        });

    }

    private void loadTimes(){
        for (GroupRoomTime groupRoomTime : BackendCaller.inst().getGroupRoomTimes(roomId)) {
            addTime(groupRoomTime);
        }
    }

    // This method adds the 'time' (hh:mm-hh-mm) it gets as input into the view so that they can be viewed and selected
    private void addTime(GroupRoomTime groupRoomTime){
        TimeSelectButton selectButton = new TimeSelectButton(groupRoomTime);
        selectButton.setOnMousePressed(e->{
            if (focused != null){
                focused.setId("");
            }
            focused = selectButton;
            focused.setId("time-select-button-focused");
        });

        timesVbox.getChildren().add(selectButton);
    }


}

