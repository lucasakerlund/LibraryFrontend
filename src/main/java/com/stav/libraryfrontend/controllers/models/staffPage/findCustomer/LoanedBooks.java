package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class LoanedBooks extends ScrollPane {

    @FXML
    private FlowPane content;

    private static LoanedBooks instance = new LoanedBooks();

    private LoanedBooks(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/loanedBooks.fxml"));
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

    }

    public void addBookBox(LoanedBookBox lbb){
        content.getChildren().add(lbb);
    }

    public static LoanedBooks inst(){
        return instance;
    }

}
