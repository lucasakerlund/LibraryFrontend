package com.stav.libraryfrontend.controllers.models.myPage.reservedBooks;

import com.stav.libraryfrontend.abstracts.BackendCaller;
import com.stav.libraryfrontend.abstracts.UserDetails;
import com.stav.libraryfrontend.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class ReservedBookCover extends StackPane {

    @FXML
    private ImageView imageView;
    @FXML
    private Label leaveQueueButton;

    private Book book;

    public ReservedBookCover(Book book) throws IOException {
        this.book = book;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/myPage/reservedBooks/reservedBookCover.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        setup();
    }

    private void setup(){
        imageView.setImage(new Image(book.getImageSrc()));

        leaveQueueButton.setOnMousePressed(e -> {
            BackendCaller.inst().leaveQueue(book.getIsbn(), UserDetails.inst().getCustomer().getCustomerId());
            ReservedBooksView.inst().deleteBook(this);
        });
    }

}
