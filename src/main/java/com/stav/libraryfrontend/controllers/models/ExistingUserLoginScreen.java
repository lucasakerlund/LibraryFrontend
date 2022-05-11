package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.UserDetails;
import com.stav.libraryfrontend.models.Customer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ExistingUserLoginScreen extends BorderPane {

    private static ExistingUserLoginScreen instance = new ExistingUserLoginScreen();

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label backButton;

    @FXML
    private Label forgottenPasswordButton;

    @FXML
    private Label loginButton;

    @FXML
    private Label errorLabel;


    private ExistingUserLoginScreen(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("fxml/existingUserLoginScreen.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    // "Action-listeners here! Match label names with corresponding action
    public void setup(){
        clearOldInfo();

        backButton.setOnMousePressed(e -> {
            Library.inst().setContent(LoginScreen.inst());
        });
        loginButton.setOnMousePressed(e -> {
            if (emailField.getText().equals("") || passwordField.getText().equals("")){
                errorLabel.setText("Vänligen fyll i båda textfälten...");
                return;
            }

            Customer customer = BackendCaller.inst().loginCustomer(emailField.getText(), passwordField.getText());
            if(customer == null){
                errorLabel.setText("Felaktig e-post adress ELLER lösenord, försök igen...");
                return;
            }

            clearOldInfo();
            UserDetails.inst().setCustomer(customer);
            Library.inst().setContent(CustomerMenu.inst());

        });
    }

    public void clearOldInfo(){
        errorLabel.setText("");
        passwordField.clear();
        emailField.clear();
    }

    public static ExistingUserLoginScreen inst(){
        return instance;
    }


}