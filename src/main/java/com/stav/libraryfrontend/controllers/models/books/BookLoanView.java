package com.stav.libraryfrontend.controllers.models.books;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BookLoanView extends BorderPane {

    private static BookLoanView instance = new BookLoanView();

    private BookLoanView(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/books/bookLoanView.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setup();
    }

    public static BookLoanView inst(){
        return instance;
    }

    private void setup(){

    }

}
