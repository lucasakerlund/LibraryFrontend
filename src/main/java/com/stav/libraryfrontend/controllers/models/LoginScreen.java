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

        loginButton.setOnMousePressed(e -> {
            Library.inst().setContent(ExistingUserLoginScreen.inst());
        });

        adminButton.setOnMousePressed(e -> {
            Library.inst().setContent(AdminScreen.inst());
        });
    }

    public static LoginScreen inst(){
        return instance;
    }
}
