package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminScreen extends BorderPane {

    private static AdminScreen instance = new AdminScreen();

    @FXML
    private Label backButtons;

    @FXML
    private Label adminLoginButton;

    @FXML
    private Label adminForgotPasswordButton;


    private AdminScreen() {
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("fxml/adminScreenLogin.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        setup();
    }

    public void setup() {
        backButtons.setOnMousePressed(e -> {
            Library.inst().setContent(LoginScreen.inst());
        });
    }

    public static AdminScreen inst() {
        return instance;
    }

}
