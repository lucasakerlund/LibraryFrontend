package com.stav.libraryfrontend.controllers.models.groupRooms;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.models.GroupRoom;
import com.stav.libraryfrontend.models.LibraryModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public List<JSONObject> getLibraries(){
       List<JSONObject> libraries = BackendCaller.inst().getLibraries();
        return libraries;
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
                    contentFlowPane.getChildren().add(new RoomBox(allGroupRooms.get(i).getRoom_id(),
                            allGroupRooms.get(i).getName(), allGroupRooms.get(i).getDescription(),
                            roomsWithLibraryName.get(i).getString("libraryName")));
                }
            }
        }

        else {
            for (int i = 0; i < allGroupRooms.size(); i++) {
                contentFlowPane.getChildren().add(new RoomBox(allGroupRooms.get(i).getRoom_id(),
                        allGroupRooms.get(i).getName(), allGroupRooms.get(i).getDescription(),
                        roomsWithLibraryName.get(i).getString("libraryName")));
            }
        }
    }

    public static GroupRooms inst(){
        return instance;
    }

}
