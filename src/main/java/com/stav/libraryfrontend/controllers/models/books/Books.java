package com.stav.libraryfrontend.controllers.models.books;

import com.stav.libraryfrontend.abstracts.BackendCaller;
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

        try {
            addBook(new BookCover(BackendCaller.inst().getImage(1)));
            addBook(new BookCover(BackendCaller.inst().getImage(2)));
            addBook(new BookCover(BackendCaller.inst().getImage(3)));
            addBook(new BookCover(BackendCaller.inst().getImage(4)));
            addBook(new BookCover(BackendCaller.inst().getImage(5)));
            addBook(new BookCover(BackendCaller.inst().getImage(6)));
            addBook(new BookCover(BackendCaller.inst().getImage(7)));
            addBook(new BookCover(BackendCaller.inst().getImage(8)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Books inst(){
        return instance;
    }

    public void addBook(BookCover bookCover){
        box.getChildren().add(bookCover);
    }

}
