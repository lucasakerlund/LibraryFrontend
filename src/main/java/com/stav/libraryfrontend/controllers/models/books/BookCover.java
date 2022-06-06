package com.stav.libraryfrontend.controllers.models.books;

import com.stav.libraryfrontend.abstracts.SubSceneHandler;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class BookCover extends StackPane {

    @FXML
    private ImageView imageView;

    @FXML
    private Label titleLabel;

    private Book book;

    public BookCover(Book book) throws IOException {
        this.book = book;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/books/bookCover.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        imageView.setImage(new Image(book.getImageSrc()));

        setup();
    }

    private void setup(){
        this.setOnMousePressed(e -> {
            SubSceneHandler.inst().show(new BookLoanView(book));
        });
        titleLabel.setText(book.getTitle());
    }

}
