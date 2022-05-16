package com.stav.libraryfrontend.controllers.models.staffPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StaffBookPage extends BorderPane {

    private static StaffBookPage instance = new StaffBookPage();

    @FXML
    private BorderPane staffContent;

    @FXML
    private Label availableBooksButton;

    @FXML
    private Label addBook;

    public StaffBookPage(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/staffBookPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static StaffBookPage inst(){
        return instance;
    }
}

