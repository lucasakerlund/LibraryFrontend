package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Objects;

public class AddStaffScreen extends BorderPane {

    private static AddStaffScreen instance = new AddStaffScreen();

    @FXML
    private CheckBox adminCheckBox;

    @FXML
    private Label backButton;

    @FXML
    private Label createStaffButton;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label messageLabel;


    private AddStaffScreen(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/addStaffScreen.fxml"));
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
        backButton.setOnMousePressed(e -> {
            Library.inst().setContent(AdminScreen.inst());
            messageLabel.setText("");
        });

        createStaffButton.setOnMousePressed(e -> {
            messageLabel.setText("Ny anställd tillagd i systemet!");
        });
    }

    public static AddStaffScreen inst(){
        return instance;
    }
}
