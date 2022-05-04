package com.stav.libraryfrontend.controllers.models.myPage;

import javafx.scene.layout.BorderPane;

public class MyPageLoanedBooks extends BorderPane {

    private static MyPageLoanedBooks instance = new MyPageLoanedBooks();

    private MyPageLoanedBooks(){
        instance = this;


    }

    public static MyPageLoanedBooks inst(){
        return instance;
    }
}
