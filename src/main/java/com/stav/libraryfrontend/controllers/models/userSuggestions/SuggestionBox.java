package com.stav.libraryfrontend.controllers.models.userSuggestions;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.SubScene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SuggestionBox extends BorderPane {

    private static SuggestionBox instance = new SuggestionBox();

    @FXML
    private Label pressMeButton;

    private SuggestionBox(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("/com/stav/libraryfrontend/fxml/userSuggestions/suggestionBox.fxml"));
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
        pressMeButton.setOnMousePressed(e->{
            SubSceneHandler.inst().show(SuggestionView.inst());
        });
    }

    public static SuggestionBox inst(){
        return instance;
    }
}
