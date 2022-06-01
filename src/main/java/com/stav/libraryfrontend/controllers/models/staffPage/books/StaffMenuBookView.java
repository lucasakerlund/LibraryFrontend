package com.stav.libraryfrontend.controllers.models.staffPage.books;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.List;

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
        updateBooks();
    }

    public void updateBooks(){
        box.getChildren().clear();
        System.out.println("tjenare " + StaffBookPage.inst().getInput());
        List<Book> books = BackendCaller.inst().getBooks("", "", "", "", StaffBookPage.inst().getInput(), "");
        for (Book book : books) {
            System.out.println(book);
            addBook(new StaffMenuBookCover(book));
        }
    }

     public void addBook(StaffMenuBookCover staffMenuBookCover){
        box.getChildren().add(staffMenuBookCover);
    }

    public static StaffMenuBookView inst(){
        return instance;
    }
}
