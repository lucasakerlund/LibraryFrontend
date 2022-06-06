package com.stav.libraryfrontend.controllers.models;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class BankID extends BorderPane {

    private static BankID instance = new BankID();

    @FXML
    private Label confirmButton;

    @FXML
    private Label cancelButton;

    @FXML
    private ImageView imageView;

    private Consumer<?> action;

    private BankID(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/bankID.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImage(new Image(getClass().getResource("/com/stav/libraryfrontend/images/bankid-960x487 2.jpg").toExternalForm()));

        setup();
    }

    public void setup(){
        confirmButton.setOnMousePressed(e -> {
            action.accept(null);
        });

        cancelButton.setOnMousePressed(e -> {
            SubSceneHandler.inst().hide();
        });
    }

    public void setConfirmListener(Consumer<Void> action){
        this.action = action;
    }

    public static BankID inst(){
        return instance;
    }

}
