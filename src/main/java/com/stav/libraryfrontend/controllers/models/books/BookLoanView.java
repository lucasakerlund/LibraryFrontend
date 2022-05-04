package com.stav.libraryfrontend.controllers.models.books;

import javafx.scene.layout.BorderPane;

public class BookLoanView extends BorderPane {

    private static BookLoanView instance = new BookLoanView();

    private BookLoanView(){

    }

    public static BookLoanView inst(){
        return instance;
    }

}
