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
            box.getChildren().add(new BookCover(BackendCaller.inst().getImage(1)));
            box.getChildren().add(new BookCover(BackendCaller.inst().getImage(2)));
            box.getChildren().add(new BookCover(BackendCaller.inst().getImage(3)));
            box.getChildren().add(new BookCover(BackendCaller.inst().getImage(4)));
            box.getChildren().add(new BookCover(BackendCaller.inst().getImage(5)));
            box.getChildren().add(new BookCover(BackendCaller.inst().getImage(6)));
            box.getChildren().add(new BookCover(BackendCaller.inst().getImage(7)));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Books inst(){
        return instance;
    }

}
