package com.stav.libraryfrontend;

import com.stav.libraryfrontend.controllers.models.LoginScreen;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Library extends Application {

    private static Library instance;

    private Stage stage;
    private BorderPane root;

    @Override
    public void start(Stage stage) throws IOException {
        instance = this;
        this.stage = stage;
        root = LoginScreen.inst();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Library inst(){
        return instance;
    }

    public Stage getStage(){
        return stage;
    }

    public BorderPane getRoot(){
        return root;
    }

    public void setContent(Parent parent){
        stage.getScene().setRoot(parent);
    }

    public static void main(String[] args) {
        launch();
    }
}