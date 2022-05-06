package com.stav.libraryfrontend.controllers.models.myPage;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class LoanedBooksView extends BorderPane{

    private static LoanedBooksView instance = new LoanedBooksView();

    @FXML
    private FlowPane box;

    public LoanedBooksView(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/loanedBooksView.fxml"));
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
        //addBook(new LoanedBookCover(BackendCaller.inst().getImage(12)));
    }

    public void addBook(LoanedBookCover loanedBookCover){
        box.getChildren().add(loanedBookCover);
    }

    public static LoanedBooksView inst(){
        return instance;
    }
}
