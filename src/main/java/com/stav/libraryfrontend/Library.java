package com.stav.libraryfrontend;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        FXMLLoader loader = new FXMLLoader(Library.class.getResource("loginScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static Library inst(){
        return instance;
    }

    public void setContent(Parent parent){
        stage.getScene().setRoot(parent);
    }

    public static void main(String[] args) {
        launch();
    }
}