package com.stav.libraryfrontend.controllers.models.staffPage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StaffAddBookPage extends BorderPane {

    private static StaffAddBookPage instance = new StaffAddBookPage();

    @FXML
    private BorderPane staffAddBookContent;

    @FXML
    private TextField isbnField;

    @FXML
    private Label addButton;


    public StaffAddBookPage(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/staff/staffAddBookPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static StaffAddBookPage inst(){
        return instance;
    }
}