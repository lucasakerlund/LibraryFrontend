package com.stav.libraryfrontend.controllers.models.staffPage.books;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class StaffMenuBookView extends BorderPane {

    @FXML
    private FlowPane box;

    private static StaffMenuBookView instance = new StaffMenuBookView();

    private StaffMenuBookView(){
        instance = this;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/books/staffMenuBookView.fxml"));
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
        StaffMenuBookCover staffMenuBookCover = new StaffMenuBookCover(BackendCaller.inst().getBook("9780262140874"));
        addBook(staffMenuBookCover);
    }

     public void addBook(StaffMenuBookCover staffMenuBookCover){
        box.getChildren().add(staffMenuBookCover);
    }

    public static StaffMenuBookView inst(){
        return instance;
    }
}
