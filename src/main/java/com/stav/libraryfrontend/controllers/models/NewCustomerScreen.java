package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class NewCustomerScreen extends BorderPane {

    private static NewCustomerScreen instance = new NewCustomerScreen();

    @FXML
    private Label createAccountButton;

    @FXML
    private Label backButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private TextField emailTextField;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private PasswordField passwordField;

    private NewCustomerScreen(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("fxml/newCustomerScreen.fxml"));
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
            firstNameTextField.clear();
            lastNameTextField.clear();
            emailTextField.clear();
            passwordField.clear();
            confirmPasswordField.clear();
            Library.inst().setContent(LoginScreen.inst());
        });

        createAccountButton.setOnMousePressed(e -> {
            LoginScreen.inst().userAddedSuccessfully();
            Library.inst().setContent(LoginScreen.inst());
        });
    }

    public static NewCustomerScreen inst(){
        return instance;
    }

}
