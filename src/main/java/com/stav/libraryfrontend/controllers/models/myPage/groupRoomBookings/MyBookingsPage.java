package com.stav.libraryfrontend.controllers.models.myPage.groupRoomBookings;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.UserDetails;
import com.stav.libraryfrontend.controllers.models.groupRooms.RoomBox;
import com.stav.libraryfrontend.models.RoomBooking;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MyBookingsPage extends BorderPane {

    private static MyBookingsPage instance = new MyBookingsPage();

    @FXML
    private FlowPane flowPane;

    private MyBookingsPage(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/groupRoomBookings/myBookingsView2.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    private void setup(){

    }

    public void loadBookings(){
        flowPane.getChildren().clear(); // Removes other users bookings

        List<JSONObject> allUserTimes = BackendCaller.inst().getUsersGroupRoomTimesById(UserDetails.inst().getCustomer().getCustomerId());

        for (int i = 0; i < allUserTimes.size(); i++){
            flowPane.getChildren().add(new BookingBox(allUserTimes.get(i).getString("name"), allUserTimes.get(i).getString("date"),
                    allUserTimes.get(i).getString("time")));
        }
    }

    public static MyBookingsPage inst(){
        return instance;
    }
}
