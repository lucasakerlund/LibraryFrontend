package com.stav.libraryfrontend.controllers.models.books;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class Books extends BorderPane {

    private static Books instance = new Books();

    @FXML
    private FlowPane box;

    private Books(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/books/books.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateBooks();
    }

    public static Books inst(){
        return instance;
    }

    public void updateBooks(){
        for(Book book : BackendCaller.inst().getBooks()) {
            try {
                addBook(new BookCover(book));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addBook(BookCover bookCover){
        box.getChildren().add(bookCover);
    }

}
