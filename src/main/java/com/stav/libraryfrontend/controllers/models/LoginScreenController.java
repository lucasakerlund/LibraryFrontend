package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LoginScreenController extends BorderPane {

    private static LoginScreenController instance = new LoginScreenController();

    @FXML
    private Label adminButton;

    @FXML
    private Label exitButton;

    @FXML
    private Label loginButton;

    @FXML
    private Label newAccountButton;


    private LoginScreenController(){
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

    public void setup(){
        exitButton.setOnMousePressed(e -> {
            System.exit(0);
        });


    }

    public static LoginScreenController inst(){
        return instance;
    }
}
