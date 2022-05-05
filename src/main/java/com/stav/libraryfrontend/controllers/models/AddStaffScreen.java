package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.Objects;

public class AddStaffScreen extends StackPane {

    // Som tur är är denna klass INTE committad än.

    // Fråga Lucas om hjälp angående knapparna och att setta "securityInput" via lambda action-listiners
    // Få översta BorderPanen att svara på korrekt inmatning och gör den osynlig vid korrekt kod...

    private static AddStaffScreen instance = new AddStaffScreen();

    // *************************** TESTING **************************
    private String securityInput = "0";

    public String getSecurityInput() {
        return securityInput;
    }

    public void setSecurityInput(String securityInput) {
        this.securityInput = securityInput;
    }
    // *************************** TESTING **************************

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

    @FXML
    private BorderPane addStaffPane;




    @FXML
    private BorderPane securityCheckPane;

    @FXML
    private Label security1Button;

    @FXML
    private Label security2Button;

    @FXML
    private Label security3Button;

    @FXML
    private Label security4Button;

    @FXML
    private Label security5Button;

    @FXML
    private Label security6Button;

    @FXML
    private Label security7Button;

    @FXML
    private Label security8Button;

    @FXML
    private Label security9Button;

    @FXML
    private Label securityConfirmButton;

    @FXML
    private Label currentInputLabel;

    @FXML
    private Label securityInputMessageLabel;

    @FXML
    private Label securityBackButton;


    private AddStaffScreen(){
        // setSecurityInput("");
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

        // IF LOGIN == "1337" make securityCheckPane invisible and addStaffPane visible
    }

    public void setup(){

        System.out.println("Security input = " + getSecurityInput());

        // currentInputLabel.setText(currentInputLabel.getText() + " " + securityInput);

        security1Button.setOnMousePressed(e -> {
            setSecurityInput(getSecurityInput() + "1");
            System.out.println("Security input = " + getSecurityInput());
        });

        security1Button.setOnMousePressed(e -> {
            setSecurityInput(getSecurityInput() + "2");
        });

        security1Button.setOnMousePressed(e -> {
            setSecurityInput(getSecurityInput() + "3");
        });

        security1Button.setOnMousePressed(e -> {
            setSecurityInput(getSecurityInput() + "4");
        });

        security1Button.setOnMousePressed(e -> {
            setSecurityInput(getSecurityInput() + "5");
        });

        security1Button.setOnMousePressed(e -> {
            setSecurityInput(getSecurityInput() + "6");
        });

        security1Button.setOnMousePressed(e -> {
            setSecurityInput(getSecurityInput() + "7");
        });

        security1Button.setOnMousePressed(e -> {
            setSecurityInput(getSecurityInput() + "8");
        });

        security1Button.setOnMousePressed(e -> {
            setSecurityInput(getSecurityInput() + "9");
        });

        securityConfirmButton.setOnMousePressed(e -> {
            if (getSecurityInput().equals("1337")){
                securityCheckPane.setVisible(false);
                addStaffPane.setVisible(true);
            } else {
                securityInputMessageLabel.setText("FELAKTIG INMATNING! Försök igen...");
            }
            setSecurityInput("");
        });

        securityBackButton.setOnMousePressed(e -> {
            setSecurityInput("0");
            Library.inst().setContent(LoginScreen.inst());
        });



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
