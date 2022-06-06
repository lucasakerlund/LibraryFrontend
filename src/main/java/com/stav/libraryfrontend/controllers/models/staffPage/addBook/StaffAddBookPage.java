package com.stav.libraryfrontend.controllers.models.staffPage.addBook;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
    private TextArea descriptionField;

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

    @FXML
    private Label succeedLabel;


    public StaffAddBookPage(){

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/addBook/staffAddBookPage.fxml"));
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
            if (titleField.getText().equals("") || descriptionField.getText().equals("") || authorField.getText().equals("") || genreField.getText().equals("") || isbnField.getText().equals("") || lastPublishedField.getText().equals("")
                    || amountOfPagesField.getText().equals("") || languageField.getText().equals("") || imageField.getText().equals("")){
                errorLabel.setText("VÄNLIGEN SKRIV IN I FÄLTEN!");
                errorLabel.setVisible(true);
                return;
            }

            if(!correctDateFormat()){
                errorLabel.setText("Formatet på datumet måste vara yyyy-MM-dd");
                errorLabel.setVisible(true);
                return;
            }

            boolean succeed = BackendCaller.inst().addBook(titleField.getText(), descriptionField.getText(), authorField.getText(), genreField.getText(), isbnField.getText(), lastPublishedField.getText(), Integer.parseInt(amountOfPagesField.getText()), languageField.getText(), imageField.getText());
            if(!succeed){
                errorLabel.setText("Något fel inträffade, försök igen.");
                errorLabel.setVisible(true);
                return;
            }
            errorLabel.setVisible(false);
            succeedLabel.setText("Boken blev tillagd.");
            succeedLabel.setVisible(true);
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Platform.runLater(() -> {
                    succeedLabel.setVisible(false);
                });
            }).start();
        });

    }

    private boolean correctDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if(lastPublishedField.getText().equals("")){
            return true;
        }
        try {
            sdf.parse(lastPublishedField.getText());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static StaffAddBookPage inst(){
        return instance;
    }
}