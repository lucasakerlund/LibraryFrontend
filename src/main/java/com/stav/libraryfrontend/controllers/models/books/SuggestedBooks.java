package com.stav.libraryfrontend.controllers.models.books;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.controllers.models.CustomerMenu;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SuggestedBooks extends BorderPane {

    private static SuggestedBooks instance = new SuggestedBooks();

    private String[] bookGenres;

    public String[] getBookGenres() {
        return bookGenres;
    }

    public void setBookGenre(String[] bookGenres) {
        this.bookGenres = bookGenres;
    }

    @FXML
    private FlowPane content;

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
        content.getChildren().clear();
        for (Book book : BackendCaller.inst().getBooksByGenre(bookGenres)) {
            try {
                content.getChildren().add(new BookCover(book));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static SuggestedBooks inst(){
        return instance;
    }

}
