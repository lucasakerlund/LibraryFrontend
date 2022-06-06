package com.stav.libraryfrontend.controllers.models.staffPage.bookSuggestions;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.models.BookSuggestion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class StaffBookSuggestionCover extends BorderPane {

    @FXML
    private Label titleLabel;
    @FXML
    private Label authorLabel;
    @FXML
    private Label isbnLabel;
    @FXML
    private Label languageLabel;
    @FXML
    private Label removeButton;

    private BookSuggestion bookSuggestion;

    public StaffBookSuggestionCover(BookSuggestion bookSuggestion){
        this.bookSuggestion = bookSuggestion;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/bookSuggestions/staffBookSuggestionCover.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    private void setup(){
        removeButton.setOnMousePressed(e -> {
            BackendCaller.inst().removeBookSuggestion(bookSuggestion.getBookSuggestionId());
            StaffBookSuggestionsPage.inst().loadSuggestions();
        });
        titleLabel.setText(bookSuggestion.getTitle());
        authorLabel.setText(bookSuggestion.getAuthor());
        isbnLabel.setText(bookSuggestion.getIsbn());
        languageLabel.setText(bookSuggestion.getLanguage());
    }

}
