package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BasicInfo extends BorderPane {

    @FXML
    private Label accountNameLabel;

    @FXML
    private Label firstName;

    @FXML
    private Label lastName;

    @FXML
    private Label customerId;

    public BasicInfo(int id, String email, String fName, String lName){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/basicInfo.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        customerId.setText("Kund ID: " + id);
        accountNameLabel.setText(email);
        firstName.setText(fName);
        lastName.setText(lName);

        setup();
    }

    public void setup(){

    }
}
