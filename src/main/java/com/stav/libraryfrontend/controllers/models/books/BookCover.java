package com.stav.libraryfrontend.controllers.models.books;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class BookCover extends StackPane {

    @FXML
    private ImageView imageView;

    public BookCover(String imageSrc) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/books/bookCover.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        imageView.setImage(new Image(imageSrc));
    }

}
