package com.stav.libraryfrontend.controllers.models.staffPage.groupRooms;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.controllers.models.staffPage.findCustomer.GroupRoomBox;
import com.stav.libraryfrontend.models.GroupRoom;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaffGroupRooms extends BorderPane {

    private static StaffGroupRooms instance = new StaffGroupRooms();

    @FXML
    private FlowPane contentFlowPane;

    @FXML
    private ComboBox libraryComboBox;

    @FXML
    private Label showAllRooms;

    private StaffGroupRooms(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/groupRooms/staffGroupRooms.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        fillComboBox();
        setup();
    }

    public void setup(){
        showAllRooms.setOnMousePressed(e -> {
            loadRooms();
        });
    }

    public void fillComboBox(){
        libraryComboBox.getItems().add("Alla bibliotek");
        libraryComboBox.setValue("Alla bibliotek");

        List<JSONObject> libraries = BackendCaller.inst().getLibraries();
        for (int i = 0; i < libraries.size(); i++) {
            libraryComboBox.getItems().add(libraries.get(i).get("name"));
        }
    }

    public void loadRooms() {
        contentFlowPane.getChildren().clear();
        List<JSONObject> allLibraries = getLibraries();
        List<GroupRoom> allGroupRooms = BackendCaller.inst().getGroupRooms();
        List<JSONObject> roomsWithLibraryName = new ArrayList<>();


        for (int i = 0; i < allGroupRooms.size(); i++) {
            JSONObject o = new JSONObject();
            o.put("name", allGroupRooms.get(i).getName());
            o.put("libraryId", allGroupRooms.get(i).getLibrary_id());

            for (int j = 0; j < allLibraries.size(); j++) {
                if (allLibraries.get(j).get("library_id") == o.get("libraryId"))
                    o.put("libraryName", allLibraries.get(j).get("name"));
            }
            roomsWithLibraryName.add(o);
        }

        if (libraryComboBox.getValue() != "Alla bibliotek") {
            for (int i = 0; i < roomsWithLibraryName.size(); i++) {
                if (roomsWithLibraryName.get(i).get("libraryName").equals(libraryComboBox.getValue())) {
                    contentFlowPane.getChildren().add(new StaffGroupRoomBox(allGroupRooms.get(i).getRoom_id(),
                            allGroupRooms.get(i).getName(), allGroupRooms.get(i).getDescription(),
                            roomsWithLibraryName.get(i).getString("libraryName")));
                }
            }
        }

        else {
            for (int i = 0; i < allGroupRooms.size(); i++) {
                contentFlowPane.getChildren().add(new StaffGroupRoomBox(allGroupRooms.get(i).getRoom_id(),
                        allGroupRooms.get(i).getName(), allGroupRooms.get(i).getDescription(),
                        roomsWithLibraryName.get(i).getString("libraryName")));
            }
        }
    }

    public List<JSONObject> getLibraries(){
        List<JSONObject> libraries = BackendCaller.inst().getLibraries();
        return libraries;
    }



    public static StaffGroupRooms inst(){
        return instance;
    }
}
