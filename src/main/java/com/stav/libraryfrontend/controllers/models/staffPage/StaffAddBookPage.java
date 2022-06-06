package com.stav.libraryfrontend.controllers.models.staffPage;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StaffAddBookPage extends BorderPane {

    private static StaffAddBookPage instance = new StaffAddBookPage();

    @FXML
    private BorderPane staffAddBookContent;

    @FXML
    private TextField isbnField;

    @FXML
    private Label addButton;

    @FXML
    private TextField titleField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField lastPublishedField;

    @FXML
    private TextField amountOfPagesField;

    @FXML
    private TextField languageField;

    @FXML
    private TextField imageField;

    @FXML
    private TextField succeedField;

    @FXML
    private Label errorLabel;


    public StaffAddBookPage(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staff/staffAddBookPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setup();
    }
    public void setup(){
        errorLabel.setText("");
        addButton.setOnMousePressed(e ->{
            BackendCaller.inst().addBook(titleField.getText(), descriptionField.getText(), authorField.getText(), genreField.getText(), isbnField.getText(), lastPublishedField.getText(), Integer.parseInt(amountOfPagesField.getText()), languageField.getText(), imageField.getText());

            if (titleField.getText().equals("") || descriptionField.getText().equals("") || authorField.getText().equals("") || genreField.getText().equals("") || isbnField.getText().equals("") || lastPublishedField.getText().equals("")
                    || amountOfPagesField.getText().equals("") || languageField.getText().equals("") || imageField.getText().equals("")){
                errorLabel.setText("VÄNLIGEN SKRIV IN I FÄLTEN!");
                return;
            }

        });

    }

    public static StaffAddBookPage inst(){
        return instance;
    }
}