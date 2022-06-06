package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.abstracts.BackendCaller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class AddStaffScreen extends StackPane {

    private static AddStaffScreen instance = new AddStaffScreen();

    private String securityInput = "";


    /* Unlocked Add staff panel components */
    @FXML
    private Label errorLabel;

    @FXML
    private CheckBox adminCheckBox;

    @FXML
    private Label backButton;

    @FXML
    private Label createStaffButton;

    @FXML
    private TextField userNameInput;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label messageLabel;

    @FXML
    private BorderPane addStaffPane;
    /* Unlocked Add staff panel components */



    /* Security check panel components */
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
    /* Security check panel components */



    private AddStaffScreen(){
        securityInput = "";
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
        errorLabel.setText("");

        /* Buttons for the locked "Add staff screen", or the security screen */
        security1Button.setOnMousePressed(e -> {
            securityInput = securityInput + 1;
            currentInputLabel.setText(currentInputLabel.getText() + " " + 1);
        });

        security2Button.setOnMousePressed(e -> {
            securityInput = securityInput + 2;
            currentInputLabel.setText(currentInputLabel.getText() + " " + 2);
        });

        security3Button.setOnMousePressed(e -> {
            securityInput = securityInput + 3;
            currentInputLabel.setText(currentInputLabel.getText() + " " + 3);
        });

        security4Button.setOnMousePressed(e -> {
            securityInput = securityInput + 4;
            currentInputLabel.setText(currentInputLabel.getText() + " " + 4);
        });

        security5Button.setOnMousePressed(e -> {
            securityInput = securityInput + 5;
            currentInputLabel.setText(currentInputLabel.getText() + " " + 5);
        });

        security6Button.setOnMousePressed(e -> {
            securityInput = securityInput + 6;
            currentInputLabel.setText(currentInputLabel.getText() + " " + 6);
        });

        security7Button.setOnMousePressed(e -> {
            securityInput = securityInput + 7;
            currentInputLabel.setText(currentInputLabel.getText() + " " + 7);
        });

        security8Button.setOnMousePressed(e -> {
            securityInput = securityInput + 8;
            currentInputLabel.setText(currentInputLabel.getText() + " " + 8);
        });

        security9Button.setOnMousePressed(e -> {
            securityInput = securityInput + 9;
            currentInputLabel.setText(currentInputLabel.getText() + " " + 9);
        });

        securityConfirmButton.setOnMousePressed(e -> {
            if (securityInput.equals("1337")){
                securityCheckPane.setVisible(false);
                addStaffPane.setVisible(true);
            } else {
                securityInputMessageLabel.setText("FELAKTIG INMATNING! Försök igen...");
                securityInput = "";
                currentInputLabel.setText("Din inmatning: ");
            }

        });

        securityBackButton.setOnMousePressed(e -> {
            securityInput = "";
            errorLabel.setText("");
            currentInputLabel.setText("Din inmatning: ");
            addStaffPane.setVisible(false);
            securityCheckPane.setVisible(true);
            Library.inst().setContent(LoginScreen.inst());
        });




        /* Buttons for the unlocked "Add staff screen" */

        backButton.setOnMousePressed(e -> {
            addStaffPane.setVisible(false);
            securityCheckPane.setVisible(true);
            securityInput = "";
            currentInputLabel.setText("Din inmatning: ");
            messageLabel.setText("");
            errorLabel.setText("");
            userNameInput.clear();
            firstNameTextField.clear();
            lastNameTextField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
            Library.inst().setContent(AdminScreen.inst());
        });

        createStaffButton.setOnMousePressed(e -> {
            if(!passwordField.getText().equals(confirmPasswordField.getText())){
                errorLabel.setText("Lösenord och upprepat lösenord stämmer inte överens med varandra");
                return;
            }

            if (userNameInput.getText().equals("") ||firstNameTextField.getText().equals("") || lastNameTextField.getText().equals("")
            || passwordField.getText().equals("") || confirmPasswordField.getText().equals("")){
                errorLabel.setText("Samtliga textfält är obligatoriska och får inte lämnas blanka");
                return;
            }

            boolean response = BackendCaller.inst().createStaff(firstNameTextField.getText(), lastNameTextField.getText(),
                    userNameInput.getText(), passwordField.getText(), adminCheckBox.isSelected() ? "ADMIN" : "LIBRARIAN");
            if(!response){
                errorLabel.setText("Det föreslagna kontot existerar redan...");
                return;
            }

            messageLabel.setText("Ny anställd tillagd i systemet!");
        });
    }

    public static AddStaffScreen inst(){
        return instance;
    }
}
