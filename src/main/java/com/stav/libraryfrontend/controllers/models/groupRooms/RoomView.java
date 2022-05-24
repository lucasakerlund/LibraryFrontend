package com.stav.libraryfrontend.controllers.models.groupRooms;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.models.GroupRoomTimes;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomView extends BorderPane {

    @FXML
    private Label roomName;

    private int room_id;

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    @FXML
    private TextArea descriptionArea;

    @FXML
    private Label bookRoomButton;

    @FXML
    private VBox timesVbox;

    @FXML
    private Label messageLabel;

    private TimeSelectButton focused;

    public RoomView(int id, String name, String description) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/groupRooms/roomView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setRoom_id(id);
        roomName.setText(name);
        descriptionArea.setText(description);

        // Hides message label by default until it is needed
        messageLabel.setText("");
        setup();
    }

    public void setup() {
        // Empty list, will later be filled with all available bookable times for THIS room
        List<GroupRoomTimes> timesForThisRoom = new ArrayList<>();

        // List that contains times for every single group room in database
        List<GroupRoomTimes> allTimes = BackendCaller.inst().getGroupRoomTimes();

        // Loops through allTimes to find the ones specific to this room (sorted by id from constructor)
        for (int i = 0; i < allTimes.size(); i++){
            if (allTimes.get(i).getRoom_id() == getRoom_id()){
                timesForThisRoom.add(allTimes.get(i));
            }
        }

        // All available times for THIS specific room are added to the view so that they can be selected and booked
        for (int i = 0; i < timesForThisRoom.size(); i++){
            addTime(timesForThisRoom.get(i).getTime());
        }


        // This button must remove the available time from the room and save it for the current user
        bookRoomButton.setOnMousePressed(e->{
            // If nothing is selected, tell user
            if(focused == null){
                messageLabel.setId("room-view-message-label");
                messageLabel.setText("Ett rum måste markeras först!");
            } else {
                System.out.println(focused.getTime());
                messageLabel.setId("room-view-message-label-green");
                messageLabel.setText("Du har bokat tiden: " + focused.getTime());
                timesVbox.getChildren().remove(focused);
                // Removes highlight
                focused = null;
            }
        });

    }

    // This method adds the 'time' (hh:mm-hh-mm) it gets as input into the view so that they can be viewed and selected
    private void addTime(String time){
        TimeSelectButton selectButton = new TimeSelectButton(time);
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

