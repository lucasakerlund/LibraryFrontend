package com.stav.libraryfrontend.controllers.models.userSuggestions;

import com.stav.libraryfrontend.Library;
import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class SuggestionView extends BorderPane {

    private static SuggestionView instance = new SuggestionView();

    @FXML
    private TextField authorField;

    @FXML
    private Label backButton;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField isbnField;

    @FXML
    private Label suggestButton;

    @FXML
    private TextField titleField;

    private SuggestionView(){
        FXMLLoader fxmlLoader = new FXMLLoader(Library.class.getResource("/com/stav/libraryfrontend/fxml/userSuggestions/suggestionView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        errorLabel.setText("");
        setup();
    }

    public void setup(){
        backButton.setOnMousePressed(e->{
            errorLabel.setText("");
            SubSceneHandler.inst().hide();
        });

        suggestButton.setOnMousePressed(e->{
            if (titleField.getText().equals("")){
                errorLabel.setText("Du måste fylla i bokens titel!");
            } else {
                /* SUGGESTION CODE HERE!! -> Backend -> Databas
                Send this to backend, bro
                titleField.getText();
                authorField.getText();
                isbnField.getText(); */

                // Placeholder
                System.out.println("WOOP, du skickade in ett förslag! Duktig ponke!");
                errorLabel.setText("");
                SubSceneHandler.inst().hide();
            }
        });
    }

    public static SuggestionView inst(){
        return instance;
    }
}
