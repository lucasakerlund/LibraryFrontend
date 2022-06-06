package com.stav.libraryfrontend.controllers.models.staffPage.books;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class StaffMenuBookCover extends StackPane{

    @FXML
    private ImageView imageView;

    private Book book;

    public StaffMenuBookCover(Book book){
        this.book = book;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/books/staffMenuBookCover.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageView.setImage(new Image(book.getImageSrc()));

        setup();
    }

    public void setup(){
        imageView.setOnMousePressed(e-> {
            SubSceneHandler.inst().show(new StaffBookInfoView(book));
        });
    }
}
