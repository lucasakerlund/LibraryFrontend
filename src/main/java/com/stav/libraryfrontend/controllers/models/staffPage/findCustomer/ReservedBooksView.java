package com.stav.libraryfrontend.controllers.models.staffPage.findCustomer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class ReservedBooksView extends BorderPane {
    @FXML
    private Label authorLabel;

    @FXML
    private ImageView image;

    @FXML
    private Label isbnLabel;

    @FXML
    private Label queueTimeLabel;

    @FXML
    private Label titleLabel;

    public ReservedBooksView(String title, String author, String queueTime, String isbn, String imageSrc){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/stav/libraryfrontend/fxml/staffPage/findCustomer/reservedBooksView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        titleLabel.setText(title);
        authorLabel.setText(author);
        queueTimeLabel.setText(queueTime);
        isbnLabel.setText(isbn);
        image.setImage(new Image(imageSrc));

        setup();
    }

    public void setup(){

    }
}
