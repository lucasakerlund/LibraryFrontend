package com.stav.libraryfrontend.controllers.models.books;

import com.stav.libraryfrontend.controllers.models.CustomerMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SuggestedBooks extends BorderPane {

    private static SuggestedBooks instance = new SuggestedBooks();

    private String bookGenre;

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    @FXML
    private FlowPane contentFlowpane;

    @FXML
    private Label backButton;

    private SuggestedBooks(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/books/suggestedBooks.fxml"));
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
        backButton.setOnMousePressed(e -> {
            CustomerMenu.inst().open(Books.inst());
        });
    }

    public void loadBooks(){
        // Make this method create 6ish books that can be displayed based on "genre"
    }

    public static SuggestedBooks inst(){
        return instance;
    }

}
