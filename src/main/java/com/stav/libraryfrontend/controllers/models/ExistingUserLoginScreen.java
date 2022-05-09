package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.abstracts.UserDetails;
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
        backButton.setOnMousePressed(e -> {
            Library.inst().setContent(LoginScreen.inst());
        });
        loginButton.setOnMousePressed(e -> {
            Library.inst().setContent(CustomerMenu.inst());
            //UserDetails.inst().setCustomer(null);
        });
    }

    public static ExistingUserLoginScreen inst(){
        return instance;
    }


}