package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LoginScreen extends BorderPane {

    private static LoginScreen instance = new LoginScreen();

    @FXML
    private Label adminButton;

    @FXML
    private Label exitButton;

    @FXML
    private Label loginButton;

    @FXML
    private Label newAccountButton;

    @FXML
    private Label userMessageLabel;





    private LoginScreen(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("fxml/loginScreen.fxml"));
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
        exitButton.setOnMousePressed(e -> {
            System.exit(0);
        });

        // Every Action-listener empties the user message label so that it only appears after a new user has been added
        loginButton.setOnMousePressed(e -> {
            userMessageLabel.setText("");
            Library.inst().setContent(ExistingUserLoginScreen.inst());
        });

        adminButton.setOnMousePressed(e -> {
            userMessageLabel.setText("");
            Library.inst().setContent(AdminScreen.inst());
        });

        newAccountButton.setOnMousePressed(e -> {
            userMessageLabel.setText("");
            Library.inst().setContent(NewCustomerScreen.inst());
        });
    }

    public void userAddedSuccessfully(){
        userMessageLabel.setText("Ny användare har lagts till i systemet!");
    }

    public static LoginScreen inst(){
        return instance;
    }
}
