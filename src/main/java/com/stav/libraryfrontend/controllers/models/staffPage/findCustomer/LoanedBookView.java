package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class LoanedBookView extends BorderPane {

    @FXML
    private Label bookTitle;

    @FXML
    private ImageView coverImage;

    @FXML
    private Label authorName;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label returnDate;

    public LoanedBookView(String title, String author, String isbn, String imageSrc, String returnDateString){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/loanedBookView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        bookTitle.setText(title);
        authorName.setText(author);
        isbnLabel.setText(isbn);
        coverImage.setImage(new Image(imageSrc));
        returnDate.setText(returnDateString);

        setup();
    }

    public void setup(){

    }
}
