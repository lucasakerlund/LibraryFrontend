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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

import java.io.IOException;

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
        languageChoice.getItems().add("Svenska");
        languageChoice.getItems().add("Engelska");

        searchByChoice.getItems().add("Titel");
        searchByChoice.getItems().add("Författare");
        searchByChoice.setValue("Författare");

        searchInput.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                searchBooks();
            }
        });

        //Get all libraries and add them to libraryChoice box.
    }

    private void searchBooks(){

    }

    public void updateBooks(){
        for(Book book : BackendCaller.inst().getBooks()) {
            try {
                addBook(new BookCover(book));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addSuggestionBox();
    }

    public void addSuggestionBox(){
        box.getChildren().add(SuggestionBox.inst());
    }

    public void addBook(BookCover bookCover){
        box.getChildren().add(bookCover);
    }

}
