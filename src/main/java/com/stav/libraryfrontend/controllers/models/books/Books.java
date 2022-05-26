package com.stav.libraryfrontend.controllers.models.books;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.controllers.models.userSuggestions.SuggestionBox;
import com.stav.libraryfrontend.models.Book;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Books extends BorderPane {

    private static Books instance = new Books();

    @FXML
    private FlowPane box;

    @FXML
    private ComboBox<String> languageChoice;
    @FXML
    private TextField releaseInput;
    @FXML
    private ComboBox<String> libraryChoice;
    @FXML
    private TextField searchInput;
    @FXML
    private Label searchButton;
    @FXML
    private ComboBox<String> searchByChoice;
    @FXML
    private Label errorLabel;
    @FXML
    private VBox centerVbox;

    private Books(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/books/books.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
        updateBooks();
    }

    public static Books inst(){
        return instance;
    }

    private void setup(){
        languageChoice.getItems().add("Alla");
        languageChoice.getItems().add("Svenska");
        languageChoice.getItems().add("Engelska");
        languageChoice.setValue("Alla");

        searchByChoice.getItems().add("Titel");
        searchByChoice.getItems().add("Författare");
        searchByChoice.setValue("Titel");

        libraryChoice.setValue("Alla");

        searchButton.setOnMousePressed(e -> {
            updateBooks();
        });
        searchInput.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                updateBooks();
            }
        });
        libraryChoice.getItems().add("Alla");
        for (JSONObject library : BackendCaller.inst().getLibraries()) {
            libraryChoice.getItems().add(library.getString("name"));
        }
    }

    private boolean correctDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if(releaseInput.getText().equals("")){
            return true;
        }
        try {
            sdf.parse(releaseInput.getText());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public void updateBooks(){
        box.getChildren().clear();
        String lang = "";
        switch(languageChoice.getValue().toLowerCase()){
            case "svenska":
                lang = "SE";
                break;
            case "engelska":
                lang = "EN";
                break;
        }
        if(!correctDateFormat()){
            errorLabel.setText("Formatet på datumet måste vara yyyy-MM-dd");
            errorLabel.setVisible(true);
            return;
        }
        errorLabel.setVisible(false);
        for(Book book : BackendCaller.inst().getBooks(
                languageChoice.getValue().equalsIgnoreCase("alla") ? "" : lang,
                releaseInput.getText(),
                libraryChoice.getValue().equalsIgnoreCase("alla") ? "" : libraryChoice.getValue(),
                searchByChoice.getValue(),
                searchInput.getText())) {
            try {
                addBook(new BookCover(book));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addSuggestionBox();
    }

    public void addSuggestionBox(){
        centerVbox.getChildren().add(SuggestionBox.inst());
    }

    public void addBook(BookCover bookCover){
        box.getChildren().add(bookCover);
    }

}
